package com.zs.web.controller.admin;

import com.github.pagehelper.PageHelper;
import com.zs.common.annotation.BusinessLog;
import com.zs.common.object.ResponseVO;
import com.zs.common.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zshuo
 * @date 2018/11/7
 **/
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @BusinessLog("文章列表")
    @RequestMapping("/list")
    public ResponseVO list() {
        return ResponseUtil.success(PageHelper.getLocalPage());
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


