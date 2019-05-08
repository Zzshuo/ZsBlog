package com.zs.blog.controller;

import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.IdReqVo;
import com.zs.blog.object.PageInfo;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.TypeService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.TypePageReqVo;
import com.zs.blog.vo.request.TypeReqVo;
import com.zs.blog.vo.response.TypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zshuo
 * @date 2019/4/15
 **/
@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @BusinessLog("添加/修改分类")
    @PostMapping("/save")
    public ResponseVo save(@RequestBody TypeReqVo reqVo) {
        typeService.save(reqVo);
        return ResponseUtil.success();
    }

    @BusinessLog("删除分类")
    @PostMapping("/delete")
    public ResponseVo delete(@RequestBody IdReqVo reqVo) {
        typeService.delete(reqVo.getId());
        return ResponseUtil.success();
    }

    @BusinessLog("获取分类")
    @PostMapping("/get")
    public ResponseVo get(@RequestBody IdReqVo reqVo) {
        TypeVo typeVo = typeService.get(reqVo.getId());
        return ResponseUtil.success(typeVo);
    }

    @BusinessLog("分类列表")
    @PostMapping("/list")
    public ResponseVo list(@RequestBody TypePageReqVo reqVo) {
        PageInfo<TypeVo> pageInfo = typeService.list(reqVo);
        return ResponseUtil.success(pageInfo);
    }

    @BusinessLog("获取所有分类")
    @PostMapping("/getAllType")
    public ResponseVo getAllType() {
        List<TypeVo> list = typeService.getAll();
        return ResponseUtil.success(list);
    }
}
