package com.zs.web.controller;

import com.zs.common.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zshuo
 * @date 2018/10/9
 **/
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/1")
    public ModelAndView index(Model model) {
        return ResponseUtil.view("index");
    }

    @RequestMapping("/2")
    public String index2() {
        return "index";
    }
}
