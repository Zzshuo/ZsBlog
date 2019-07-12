package com.zs.blog.controller;

import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.consts.CommonConst;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.FileService;
import com.zs.blog.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zshuo
 * @date 2019-05-07
 **/
@Slf4j
@RestController
@RequestMapping("/admin/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @BusinessLog("上传图片")
    @PostMapping("/addImage")
    public ResponseVo addImage(@RequestParam("file") MultipartFile file, @RequestParam("paht") String path) {
        String url = fileService.addImage(file, path);
        return ResponseUtil.success(url);
    }

    @BusinessLog("上传图片")
    @PostMapping("/addArticleImage")
    public ResponseVo addArticleImage(@RequestParam("file") MultipartFile file) {
        String url = fileService.addImage(file, CommonConst.ALI_OSS_IMG_ARTICLE_PATH);
        return ResponseUtil.success(url);
    }


    @BusinessLog("上传封面图")
    @PostMapping("/addCoverImage")
    public ResponseVo addCoverImage(@RequestParam("file") MultipartFile file) {
        String url = fileService.addImage(file, CommonConst.ALI_OSS_IMG_COVER_PATH);
        return ResponseUtil.success(url);
    }
}
