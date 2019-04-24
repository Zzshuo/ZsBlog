package com.zs.blog.controller;

import com.google.common.collect.ImmutableMap;
import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.UserReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zshuo
 * @date 2019-04-24
 **/
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @BusinessLog("进入登陆页面")
    @PostMapping("/login")
    public ResponseVo login(@RequestBody UserReqVo userReqVo) {
        String username = userReqVo.getUsername();
        String password = userReqVo.getPassword();

        return ResponseUtil.success(ImmutableMap.of("token", "admin-token"));
    }

    @BusinessLog("进入登陆页面")
    @PostMapping("/info")
    public ResponseVo info() {
        // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;

        ImmutableMap<Object, Object> map = ImmutableMap.builder()
                .put("roles", new String[]{"admin"})
                .put("introduction", "I am a super administrator")
                .put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                .put("name", "Super Admin")
                .build();
        return ResponseUtil.success(map);
    }

    @BusinessLog("进入登陆页面")
    @PostMapping("/logout")
    public ResponseVo logout() {
        return ResponseUtil.success();
    }
}
