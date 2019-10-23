package com.zs.blog.controller;

import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.TagService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.TagReqVo;
import com.zs.blog.vo.response.TagDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @BusinessLog("标签列表")
    @PostMapping("/list")
    public ResponseVo list(@RequestBody TagReqVo reqVo) {
        return ResponseUtil.success(tagService.list(reqVo));
    }

    @BusinessLog("获取所有标签")
    @PostMapping("/getAllTag")
    public ResponseVo getAllTag() {
        List<TagDetailVo> list = tagService.getAll();
        return ResponseUtil.success(list);
    }
}
