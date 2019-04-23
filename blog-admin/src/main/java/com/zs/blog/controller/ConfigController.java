package com.zs.blog.controller;

import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.service.ConfigService;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.ConfigReqVo;
import com.zs.blog.vo.response.ConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @BusinessLog("保存配置")
    @PostMapping("/save")
    public ResponseVo save(@RequestBody ConfigReqVo reqVo) {
        configService.save(reqVo);
        return ResponseUtil.success();
    }

    @BusinessLog("配置列表")
    @PostMapping("/getListByType")
    public ResponseVo list(@RequestBody ConfigReqVo reqVo) {
        List<ConfigVo> list = configService.getConfigsByType(reqVo.getType());
        return ResponseUtil.success(list);
    }
}
