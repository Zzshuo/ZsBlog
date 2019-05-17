package com.zs.blog.controller;

import com.google.common.collect.ImmutableMap;
import com.zs.blog.annotation.BusinessLog;
import com.zs.blog.config.ShiroProperties;
import com.zs.blog.exception.BusinessException;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.util.ResponseUtil;
import com.zs.blog.vo.request.UserReqVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
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

    @BusinessLog("用户信息")
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

    /**
     * 登录
     */
    @BusinessLog("登陆")
    @PostMapping("/login")
    public ResponseVo login(@RequestBody UserReqVo userReqVo) {
        try {
            Subject subject = SecurityUtils.getSubject();
            String username = userReqVo.getUsername();
            String password = userReqVo.getPassword();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);

            String token = (String) subject.getSession().getId();

            return ResponseUtil.success(ImmutableMap.of("token", token));

        } catch (IncorrectCredentialsException e) {
            throw new BusinessException("密码错误");
        } catch (LockedAccountException e) {
            throw new BusinessException("该用户已被禁用");
        } catch (ExpiredCredentialsException e) {
            throw new BusinessException("已过期");
        } catch (AuthenticationException e) {
            throw new BusinessException("该用户不存在");
        }
    }

    /**
     * 注销
     */
    @BusinessLog("注销")
    @RequestMapping(value = "/logout")
    public ResponseVo logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return ResponseUtil.success();
    }

    /**
     * 未登录
     */
    @RequestMapping(value = "/unauth")
    public ResponseVo unauth() {
        return ResponseUtil.error("请重新登陆");
    }


    public static void main(String[] args) {
        ShiroProperties shiroProperties = new ShiroProperties();

        String hashAlgorithmName = shiroProperties.getHashAlgorithmName();
        int hashIterations = shiroProperties.getHashIterations();

        String credentials = "admin";
        // 用户名当做盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes("admin");
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}
