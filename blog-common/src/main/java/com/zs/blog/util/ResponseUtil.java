package com.zs.blog.util;

import com.zs.blog.object.ErrorEnum;
import com.zs.blog.object.ResponseVO;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
public class ResponseUtil {

    public static ResponseVO success() {
        return new ResponseVO<>(ErrorEnum.SUCCESS);
    }

    public static ResponseVO success(Object data) {
        return new ResponseVO<>(ErrorEnum.SUCCESS, data);
    }

    public static ResponseVO error() {
        return new ResponseVO<>(ErrorEnum.ERROR);
    }

    public static ResponseVO error(Object data) {
        return new ResponseVO<>(ErrorEnum.ERROR, data);
    }

    public static ResponseVO error(ErrorEnum state) {
        return new ResponseVO<>(state);
    }

    public static ResponseVO error(Integer code, String message) {
        return new ResponseVO<>(code, message);
    }

    public static ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    public static ModelAndView view(String view, Map<String, Object> model) {
        return new ModelAndView(view, model);
    }

    public static ModelAndView redirect(String view) {
        return new ModelAndView("redirect:" + view);
    }
}
