package com.zs.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zshuo
 * @date 2019-05-07
 **/
@Service
public interface FileService {

    /**
     * 上传图片
     *
     * @param file
     * @param basePath
     * @return 图片完整地址
     */
    String addImage(MultipartFile file, String basePath);
}
