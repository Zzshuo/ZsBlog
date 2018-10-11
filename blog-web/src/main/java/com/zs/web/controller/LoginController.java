package com.zs.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zs.common.util.ResponseUtil;
import com.zs.dao.model.User;
import com.zs.web.service.UserService;
import com.zs.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zshuo
 * @date 2018/10/11
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index() {

        return ResponseUtil.view("/login");
    }

    @ResponseBody
    @RequestMapping("/users")
    public String users(UserVo userVo, Model model) {
        PageInfo<User> users = userService.getUsers(userVo);
        model.addAttribute("list", users);
        return JSONObject.toJSONString(userService.getUserById(1));
    }

}
