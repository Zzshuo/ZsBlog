package com.zs.blog.controller;

import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.WebService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.response.WebInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@RestController
@RequestMapping("/web")
public class WebController {
    
    @Autowired
    private WebService webService;

    @BusinessLog("网站信息")
    @PostMapping("/info")
    public ResponseVo info() {
        WebInfoVo info = webService.info();
        return ResponseUtil.success(info);
    }
}
