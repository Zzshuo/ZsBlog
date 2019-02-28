package com.zs.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.TagService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.TagPageReqVo;
import com.zs.blog.vo.response.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @BusinessLog("标签列表")
    @PostMapping("/list")
    public ResponseVo list(@RequestBody TagPageReqVo reqVo) {
        PageInfo<TagVo> pageInfo = tagService.list(reqVo);
        return ResponseUtil.success(pageInfo);
    }
}
