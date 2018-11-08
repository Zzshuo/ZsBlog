package com.zs.web.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zs.common.annotation.BusinessLog;
import com.zs.common.object.ResponseVO;
import com.zs.common.util.ResponseUtil;
import com.zs.web.service.ArticleService;
import com.zs.web.vo.request.ArticleReqVo;
import com.zs.web.vo.response.ArticleVo;
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


