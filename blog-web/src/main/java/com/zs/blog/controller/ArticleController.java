package com.zs.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVO;
import com.zs.blog.service.ArticleService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.ArticleReqVo;
import com.zs.blog.vo.response.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @BusinessLog("获取文章")
    @PostMapping("/get")
    public ResponseVO get(Integer id) {
        ArticleVo articleVo = articleService.get(id);
        return ResponseUtil.success(articleVo);
    }

    @BusinessLog("文章列表")
    @PostMapping("/list")
    public ResponseVO list(@RequestBody ArticleReqVo reqVo) {
        PageInfo<ArticleVo> pageInfo = articleService.list(reqVo);
        return ResponseUtil.success(pageInfo);
    }
}
