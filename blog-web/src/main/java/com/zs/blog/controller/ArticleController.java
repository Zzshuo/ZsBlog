package com.zs.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.ArticleService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.ArticlePageReqVo;
import com.zs.blog.vo.response.ArticleDetailVo;
import com.zs.blog.vo.response.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @BusinessLog("获取文章")
    @PostMapping("/get")
    public ResponseVo get(@RequestBody Map<String, String> map) {
        Integer id = Integer.valueOf(map.get("id"));
        ArticleDetailVo articleDetailVo = articleService.get(id);
        return ResponseUtil.success(articleDetailVo);
    }

    @BusinessLog("文章列表")
    @PostMapping("/list")
    public ResponseVo list(@RequestBody ArticlePageReqVo reqVo) {
        PageInfo<ArticleVo> pageInfo = articleService.list(reqVo);
        return ResponseUtil.success(pageInfo);
    }
}
