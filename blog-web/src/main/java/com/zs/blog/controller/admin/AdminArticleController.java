package com.zs.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.ArticleService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.ArticlePageReqVo;
import com.zs.blog.vo.response.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zshuo
 * @date 2018/11/7
 **/
@Controller
@RequestMapping("/api/admin/article")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @BusinessLog("文章列表")
    @PostMapping("/list")
    @ResponseBody
    public ResponseVo list(@RequestBody ArticlePageReqVo reqVo) {
        PageInfo<ArticleVo> pageInfo = articleService.list(reqVo);
        return ResponseUtil.success(pageInfo);
    }

    @BusinessLog("删除文章")
    @PostMapping("/delete")
    public ResponseVo delete(Integer id) {
        return ResponseUtil.success();
    }

    @BusinessLog("获取文章")
    @PostMapping("/get")
    public ResponseVo get(Integer id) {
        return ResponseUtil.success();
    }

    @BusinessLog("发布/修改文章")
    @PostMapping("/addOrUpdate")
    public ResponseVo addOrUpdate() {


        return ResponseUtil.success();
    }

    @BusinessLog("彻底删除")
    @PostMapping("/remove")
    public ResponseVo remove(Integer id) {
        return ResponseUtil.success();
    }
}


