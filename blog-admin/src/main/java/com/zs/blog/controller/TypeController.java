package com.zs.blog.controller;

import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.util.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zshuo
 * @date 2019/4/15
 **/
@RestController
@RequestMapping("/api/type")
public class TypeController {

    @BusinessLog("文章分类")
    @PostMapping("/getAllType")
    public ResponseVo getArticleStateMap() {

        return ResponseUtil.success();
    }
}
