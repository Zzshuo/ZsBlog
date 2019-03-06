package com.zs.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.BaseReqVo;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.ArticleService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.util.redis.RedisUtil;
import com.zs.blog.vo.request.ArticlePageReqVo;
import com.zs.blog.vo.request.ArticleReqVo;
import com.zs.blog.vo.response.ArticleBriefVo;
import com.zs.blog.vo.response.ArticleVo;
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
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ArticleService articleService;

    @BusinessLog("发布/修改文章")
    @PostMapping("/addOrUpdate")
    public ResponseVo addOrUpdate(@RequestBody ArticleReqVo reqVo) {
        articleService.addOrUpdate(reqVo);
        return ResponseUtil.success();
    }

    @BusinessLog("删除文章")
    @PostMapping("/delete")
    public ResponseVo delete(@RequestBody BaseReqVo reqVo) {
        articleService.delete(reqVo.getId());
        return ResponseUtil.success();
    }

    @BusinessLog("获取文章")
    @PostMapping("/get")
    public ResponseVo get(@RequestBody BaseReqVo reqVo) {
        ArticleVo articleVo = articleService.get(reqVo.getId());
        return ResponseUtil.success(articleVo);
    }

    @BusinessLog("文章列表")
    @PostMapping("/list")
    public ResponseVo list(@RequestBody ArticlePageReqVo reqVo) {
        PageInfo<ArticleBriefVo> pageInfo = articleService.list(reqVo);
        return ResponseUtil.success(pageInfo);
    }
}
