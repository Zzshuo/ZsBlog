package com.zs.web.controller;

import com.zs.common.annotation.BusinessLog;
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


    @BusinessLog("index1 enter")
    @RequestMapping("/1")
    public ModelAndView index(Model model) {
        model.addAttribute("name", "zshuo");
        return ResponseUtil.view("index");
    }
}
