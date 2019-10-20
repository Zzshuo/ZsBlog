package com.zs.blog.controller;

import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.enums.ArticleEnum;
import com.zs.blog.object.IdReqVo;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.ArticleService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.ArticleByTagReqVo;
import com.zs.blog.vo.request.ArticleReqVo;
import com.zs.blog.vo.response.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@RestController
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @BusinessLog("发布/修改文章")
    @PostMapping("/save")
    public ResponseVo save(@RequestBody ArticleReqVo reqVo) {
        articleService.save(reqVo);
        return ResponseUtil.success();
    }

    @BusinessLog("删除文章")
    @PostMapping("/delete")
    public ResponseVo delete(@RequestBody IdReqVo reqVo) {
        articleService.delete(reqVo.getId());
        return ResponseUtil.success();
    }

    @BusinessLog("获取文章")
    @PostMapping("/get")
    public ResponseVo get(@RequestBody IdReqVo reqVo) {
        ArticleVo articleVo = articleService.get(reqVo.getId());
        return ResponseUtil.success(articleVo);
    }

    @BusinessLog("文章列表")
    @PostMapping("/list")
    public ResponseVo list(@RequestBody ArticleReqVo reqVo) {
        return ResponseUtil.success(articleService.list(reqVo));
    }

    @BusinessLog("根据tagId获取文章列表")
    @PostMapping("/getListByTagId")
    public ResponseVo getListByTagId(@RequestBody ArticleByTagReqVo reqVo) {
        return ResponseUtil.success(articleService.getListByTagId(reqVo));
    }

    @BusinessLog("文章状态")
    @PostMapping("/getArticleStateMap")
    public ResponseVo getArticleStateMap() {
        Map<Integer, String> map = new HashMap<>();
        for (ArticleEnum.StateEnum value : ArticleEnum.StateEnum.values()) {
            map.put(value.getId(), value.getName());
        }
        return ResponseUtil.success(map);
    }
}
