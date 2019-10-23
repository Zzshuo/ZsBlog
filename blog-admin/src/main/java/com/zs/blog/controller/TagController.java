package com.zs.blog.controller;

import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.TagService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.TagReqVo;
import com.zs.blog.vo.response.TagDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@RestController
@RequestMapping("/admin/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @BusinessLog("添加/修改标签")
    @PostMapping("/save")
    public ResponseVo save(@RequestBody TagReqVo reqVo) {
        tagService.save(reqVo);
        return ResponseUtil.success();
    }

    @BusinessLog("删除标签")
    @PostMapping("/delete")
    public ResponseVo delete(@RequestBody TagReqVo reqVo) {
        tagService.delete(reqVo);
        return ResponseUtil.success();
    }

    @BusinessLog("获取所有标签")
    @PostMapping("/getAllTag")
    public ResponseVo getAllTag() {
        List<TagDetailVo> list = tagService.getAll();
        return ResponseUtil.success(list);
    }
}
