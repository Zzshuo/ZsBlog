package com.zs.blog.controller;

import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.TypeService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.response.TypeDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

    @BusinessLog("获取所有分类")
    @PostMapping("/getAllType")
    public ResponseVo getAllType() {
        List<TypeDetailVo> list = typeService.getAll();
        return ResponseUtil.success(list);
    }
}
