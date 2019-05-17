package com.zs.blog.service.impl;

import com.zs.blog.exception.BusinessException;
import com.zs.blog.service.FileService;
import com.zs.blog.service.OssService;
import com.zs.blog.util.FileUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zshuo
 * @date 2019-05-07
 **/
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssService ossService;

    @Override
    public String addImage(MultipartFile file, String basePath) {
        if (file == null) {
            throw new BusinessException("请选择文件");
        }
        long size = file.getSize();
        if (!FileUtil.checkFileSize(size, 10, "M")) {
            throw new BusinessException("文件不能超过10M");
        }
        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();

            assert fileName != null;
            String suffix = FileUtil.getSuffix(fileName);
            if (!FileUtil.isPicture(suffix)) {
                throw new BusinessException("目前只支持以下图片格式：[jpg, jpeg, png, gif, bmp]");
            }


            String filePath = basePath + genOssFileName(fileName, suffix, size) + suffix;
            return ossService.uploadFile(filePath, inputStream);
        } catch (IOException e) {
            throw new BusinessException("文件上传失败" + e.getMessage());
        }
    }

    /**
     * 生成oss文件名, 防止重复上传
     *
     * @param filename
     * @param suffix
     * @param size
     * @return
     */
    private String genOssFileName(String filename, String suffix, long size) {
        StringBuilder sb = new StringBuilder();
        sb.append(filename)
                .append(suffix)
                .append(size);
        return DigestUtils.md5Hex(sb.toString());
    }
}
