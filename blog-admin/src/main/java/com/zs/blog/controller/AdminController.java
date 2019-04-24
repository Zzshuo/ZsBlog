package com.zs.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zshuo
 * @date 2018/10/11
 **/
@Slf4j
@Controller
@RequestMapping("/api/admin")
public class AdminController {
//
//    @BusinessLog("进入登陆页面")
//    @PostMapping("/login")
//    public ModelAndView index() {
//        return ResponseUtil.view("/admin/login");
//    }
//
//    @BusinessLog("退出登录")
//    @PostMapping("/logout")
//    public ModelAndView logout() {
//        //退出
//        SecurityUtils.getSubject().logout();
//        return ResponseUtil.view("/admin/logout");
//    }
//
//    @ApiOperation(value = "提交登陆请求", notes = "根据User对象创建用户")
//    @ApiImplicitParam(name = "userReqVo", value = "用户实体UserReqVo", required = true, dataType = "UserReqVo")
//    @BusinessLog("提交登陆请求")
//    @ResponseBody
//    @PostMapping("/submit")
//    public ResponseVo submit(@RequestBody UserReqVo userReqVo) {
//        String username = userReqVo.getUsername();
//        String password = userReqVo.getPassword();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        try {
//            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
//            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
//            // 所以这一步在调用login(token)方法时,它会走到xxRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
//            SecurityUtils.getSubject().login(token);
//            return ResponseUtil.success("登录成功！");
//        } catch (Exception e) {
//            log.error("登录失败，用户名[{}]", username, e);
//            token.clear();
//            return ResponseUtil.error(e.getMessage());
//        }
//    }
}
