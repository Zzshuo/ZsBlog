package com.zs.blog.services;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.zs.blog.enums.ConfigEnum;
import com.zs.blog.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author zshuo
 * @date 2019-04-29
 **/
public class OssService {

    private static OSSClient ossClient;

    @Autowired
    private ConfigService configService;

    public OSSClient getInstance() {
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
        if (getInstance().doesBucketExist(bucketName)) {
            return;
        }
        getInstance().createBucket(bucketName);
        // 设置bucket的访问权限，public-read-write权限
        getInstance().setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }

    /**
     * 上传文件流
     */
    public void uploadFile(String key, InputStream inputStream) {
        getInstance().putObject(getBucketName(), key, inputStream);
    }

    /**
     * 上传Byte数组
     */
    public void uploadFile(String key, byte[] bytes) {
        getInstance().putObject(getBucketName(), key, new ByteArrayInputStream(bytes));
    }

    /**
     * 上传网络流
     */
    public void uploadFile(String key, String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        getInstance().putObject(getBucketName(), key, inputStream);
    }

    /**
     * 文件上传
     */
    public void uploadFile(String key, File file) {
        getInstance().putObject(getBucketName(), key, file);
    }
}
