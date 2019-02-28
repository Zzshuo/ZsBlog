package com.zs.blog.util;

import com.zs.blog.object.ErrorEnum;
import com.zs.blog.object.ResponseVo;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
public class ResponseUtil {

    public static ResponseVo success() {
        return new ResponseVo<>(ErrorEnum.SUCCESS);
    }

    public static ResponseVo success(Object data) {
        return new ResponseVo<>(ErrorEnum.SUCCESS, data);
    }

    public static ResponseVo error() {
        return new ResponseVo<>(ErrorEnum.ERROR);
    }

    public static ResponseVo error(Object data) {
        return new ResponseVo<>(ErrorEnum.ERROR, data);
    }

    public static ResponseVo error(ErrorEnum state) {
        return new ResponseVo<>(state);
    }

    public static ResponseVo error(Integer code, String message) {
        return new ResponseVo<>(code, message);
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
