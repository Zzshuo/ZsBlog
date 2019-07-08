package com.zs.blog.service;

import com.zs.blog.vo.response.OssCallbackVo;
import com.zs.blog.vo.response.OssPolicyVo;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zshuo
 * @date 2019-05-17
 **/
public interface OssService {

    /**
     * 上传文件流
     */
    String uploadFile(String key, InputStream inputStream);

    /**
     * 上传Byte数组
     */
    String uploadFile(String key, byte[] bytes);

    /**
     * 上传网络流
     */
    String uploadFile(String key, String url) throws IOException;

    /**
     * 文件上传
     */
    String uploadFile(String key, File file);


    /**
     * oss上传策略生成
     */
    OssPolicyVo policy();

    /**
     * oss上传成功回调
     */
    OssCallbackVo callback(HttpServletRequest request);
}
