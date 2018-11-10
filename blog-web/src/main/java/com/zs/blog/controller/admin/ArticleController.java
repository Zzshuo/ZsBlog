package com.zs.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVO;
import com.zs.blog.service.ArticleService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.ArticleReqVo;
import com.zs.blog.vo.response.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zshuo
 * @date 2018/11/7
 **/
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @BusinessLog("文章列表")
    @RequestMapping("/list")
    @ResponseBody
    public ResponseVO list(@RequestBody ArticleReqVo reqVo) {
        PageInfo<ArticleVo> pageInfo = articleService.list(reqVo);
        return ResponseUtil.success(pageInfo);
    }

    @BusinessLog("删除文章")
    @RequestMapping("/delete")
    public ResponseVO delete(Integer id) {
        return ResponseUtil.success();
    }

    @BusinessLog("获取文章")
    @RequestMapping("/get")
    public ResponseVO get(Integer id) {
        return ResponseUtil.success();
    }

    @BusinessLog("发布/修改文章")
    @RequestMapping("/addOrUpdate")
    public ResponseVO addOrUpdate() {
        return ResponseUtil.success();
    }

    @BusinessLog("彻底删除")
    @RequestMapping("/remove")
    public ResponseVO remove(Integer id) {
        return ResponseUtil.success();
    }
}


