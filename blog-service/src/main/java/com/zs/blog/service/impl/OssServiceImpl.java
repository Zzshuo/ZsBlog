package com.zs.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.zs.blog.consts.CommonConst;
import com.zs.blog.enums.ConfigEnum;
import com.zs.blog.exception.BusinessException;
import com.zs.blog.service.ConfigService;
import com.zs.blog.service.OssService;
import com.zs.blog.vo.response.OssCallbackVo;
import com.zs.blog.vo.response.OssPolicyVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author zshuo
 * @date 2019-04-29
 **/
@Slf4j
@Service
public class OssServiceImpl implements OssService {

    private static OSSClient ossClient;

    @Autowired
    private ConfigService configService;

    public OSSClient ossClient() {
        if (ossClient == null) {
            synchronized (OssServiceImpl.class) {
                if (ossClient == null) {
                    String accessKeyId = configService.getConfigValueByKey(ConfigEnum.ALI_OSS_ACCESSKEY_ID.getKey());
                    String accessKeySecret = configService.getConfigValueByKey(ConfigEnum.ALI_OSS_ACCESSKEY_SECRET.getKey());
                    ossClient = new OSSClient(endPoint(), accessKeyId, accessKeySecret);
                    checkBucket();
                }
            }
        }
        return ossClient;
    }


    /**
     * 检查bucket 不存在则创建
     */
    private void checkBucket() {
        String bucketName = bucketName();
        if (ossClient().doesBucketExist(bucketName)) {
            return;
        }
        ossClient().createBucket(bucketName);
        // 设置bucket的访问权限，public-read-write权限
        ossClient().setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }

    /**
     * 上传文件流
     */
    @Override
    public String uploadFile(String key, InputStream inputStream) {
        if (!ossClient().doesObjectExist(bucketName(), key)) {
            ossClient().putObject(bucketName(), key, inputStream);
        }
        return getFullFilePath(key);
    }

    /**
     * 上传Byte数组
     */
    @Override
    public String uploadFile(String key, byte[] bytes) {
        ossClient().putObject(bucketName(), key, new ByteArrayInputStream(bytes));
        return getFullFilePath(key);
    }

    /**
     * 上传网络流
     */
    @Override
    public String uploadFile(String key, String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        ossClient().putObject(bucketName(), key, inputStream);
        return getFullFilePath(key);
    }

    /**
     * 文件上传
     */
    @Override
    public String uploadFile(String key, File file) {
        ossClient().putObject(bucketName(), key, file);
        return getFullFilePath(key);
    }

    /**
     * 获得完全访问地址
     *
     * @param key
     * @return
     */
    private String getFullFilePath(String key) {
        return "https://" + bucketName() + "." + endPoint() + File.separator + key;
    }

    private String bucketName() {
        return configService.getConfigValueByKey(ConfigEnum.ALI_OSS_BUCKET_NAME.getKey());
    }

    private String endPoint() {
        return configService.getConfigValueByKey(ConfigEnum.ALI_OSS_END_POINT.getKey());
    }

    /**
     * 签名生成
     */
    @Override
    public OssPolicyVo policy() {
        OssPolicyVo result = new OssPolicyVo();
        // 存储目录
        String dir = CommonConst.ALI_OSS_IMG_PATH;
        // 签名有效期
        long expireEndTime = System.currentTimeMillis() + CommonConst.ALI_OSS_EXPIRE * 1000;
        Date expiration = new Date(expireEndTime);
        // 文件大小
        long maxSize = CommonConst.ALI_OSS_MAX_SIZE * 1024 * 1024;
        // 回调
        OssCallbackParam callback = new OssCallbackParam();
        callback.setCallbackUrl(CommonConst.ALI_OSS_CALLBACK);
        callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        callback.setCallbackBodyType("application/x-www-form-urlencoded");
        // 提交节点
        String action = "http://" + bucketName() + "." + endPoint();
        try {
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient().generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient().calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSON.toJSONString(callback).getBytes("utf-8"));
            // 返回结果
            result.setAccessKeyId(ossClient().getCredentialsProvider().getCredentials().getAccessKeyId());
            result.setPolicy(policy);
            result.setSignature(signature);
            result.setDir(dir);
            result.setCallback(callbackData);
            result.setHost(action);
        } catch (Exception e) {
            throw new BusinessException("签名生成失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public OssCallbackVo callback(HttpServletRequest request) {
        OssCallbackVo result = new OssCallbackVo();
        String filename = request.getParameter("filename");
        filename = "http://".
                concat(bucketName()).
                concat(".")
                .concat(endPoint()).
                        concat("/").
                        concat(filename);
        result.setFilename(filename);
        result.setSize(request.getParameter("size"));
        result.setMimeType(request.getParameter("mimeType"));
        result.setWidth(request.getParameter("width"));
        result.setHeight(request.getParameter("height"));
        return result;
    }



    @Data
    private class OssCallbackParam {
        @ApiModelProperty("请求的回调地址")
        private String callbackUrl;
        @ApiModelProperty("回调是传入request中的参数")
        private String callbackBody;
        @ApiModelProperty("回调时传入参数的格式，比如表单提交形式")
        private String callbackBodyType;
    }
}
