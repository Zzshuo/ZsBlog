package com.zs.blog.services;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.zs.blog.enums.ConfigEnum;
import com.zs.blog.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author zshuo
 * @date 2019-04-29
 **/
@Service
public class OssService {

    private static OSSClient ossClient;

    @Autowired
    private ConfigService configService;

    public OSSClient ossClient() {
        if (ossClient == null) {
            synchronized (OssService.class) {
                if (ossClient == null) {
                    String endpoint = configService.getConfigValueByKey(ConfigEnum.ALI_OSS_END_POINT.getKey());
                    String accessKeyId = configService.getConfigValueByKey(ConfigEnum.ALI_OSS_ACCESSKEY_ID.getKey());
                    String accessKeySecret = configService.getConfigValueByKey(ConfigEnum.ALI_OSS_ACCESSKEY_SECRET.getKey());
                    ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                    checkBucket();
                }
            }
        }
        return ossClient;
    }

    private String getBucketName() {
        return configService.getConfigValueByKey(ConfigEnum.ALI_OSS_BUCKET_NAME.getKey());
    }

    /**
     * 检查bucket 不存在则创建
     */
    private void checkBucket() {
        String bucketName = getBucketName();
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
    public String uploadFile(String key, InputStream inputStream) {
        if (!ossClient().doesObjectExist(getBucketName(), key)) {
            ossClient().putObject(getBucketName(), key, inputStream);
        }
        return getFullFilePath(key);
    }

    /**
     * 上传Byte数组
     */
    public String uploadFile(String key, byte[] bytes) {
        ossClient().putObject(getBucketName(), key, new ByteArrayInputStream(bytes));
        return getFullFilePath(key);
    }

    /**
     * 上传网络流
     */
    public String uploadFile(String key, String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        ossClient().putObject(getBucketName(), key, inputStream);
        return getFullFilePath(key);
    }

    /**
     * 文件上传
     */
    public String uploadFile(String key, File file) {
        ossClient().putObject(getBucketName(), key, file);
        return getFullFilePath(key);
    }

    /**
     * 获得完全访问地址
     *
     * @param key
     * @return
     */
    private String getFullFilePath(String key) {
        return "https://" + getBucketName() + "." + configService.getConfigValueByKey(ConfigEnum.ALI_OSS_END_POINT.getKey()) + File.separator + key;
    }
}
