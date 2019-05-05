package com.zs.blog.controller;

import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.enums.ArticleEnum;
import com.zs.blog.enums.ConfigTypeEnum;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.util.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zshuo
 * @date 2019/4/15
 **/
@RestController
@RequestMapping("/api/common")
public class CommonController {

    @BusinessLog("文章状态")
    @PostMapping("/getArticleStateMap")
    public ResponseVo getArticleStateMap() {
        Map<Integer, String> map = new HashMap<>();
        for (ArticleEnum.StateEnum value : ArticleEnum.StateEnum.values()) {
            map.put(value.getId(), value.getName());
        }
        return ResponseUtil.success(map);
    }

    @BusinessLog("文章状态")
    @PostMapping("/getConfigTypeMap")
    public ResponseVo getConfigTypeMap() {
        Map<Integer, String> map = new HashMap<>();
        for (ConfigTypeEnum value : ConfigTypeEnum.values()) {
            map.put(value.getId(), value.getName());
        }
        return ResponseUtil.success(map);
    }
}
