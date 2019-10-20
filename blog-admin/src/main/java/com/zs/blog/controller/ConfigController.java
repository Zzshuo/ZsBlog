package com.zs.blog.controller;

import com.google.common.collect.ImmutableMap;
import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.enums.ConfigTypeEnum;
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

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @BusinessLog("保存配置")
    @PostMapping("/save")
    public ResponseVo save(@RequestBody @Valid ConfigReqVo reqVo) {
        configService.save(reqVo);
        return ResponseUtil.success();
    }

    @BusinessLog("配置列表")
    @PostMapping("/getListByType")
    public ResponseVo list(@RequestBody ConfigReqVo reqVo) {
        List<ConfigVo> list = configService.getConfigsByType(reqVo.getConfigType());
        return ResponseUtil.success(list);
    }

    @BusinessLog("配置分类")
    @PostMapping("/getAllConfigType")
    public ResponseVo getAllConfigType() {
        List<Map<Integer, String>> collect = Arrays.stream(ConfigTypeEnum.values())
                .map(value -> new ImmutableMap.Builder<Integer, String>().put(value.getId(), value.getName()).build())
                .collect(Collectors.toList());
        return ResponseUtil.success(collect);
    }
}
