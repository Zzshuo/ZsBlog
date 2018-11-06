package com.zs.common.util;

import com.zs.common.object.ResponseVO;
import com.zs.common.object.State;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
public class ResponseUtil {

    public static ResponseVO success() {
        return new ResponseVO<>(State.SUCCESS);
    }

    public static ResponseVO success(Object data) {
        return new ResponseVO<>(State.SUCCESS, data);
    }

    public static ResponseVO error() {
        return new ResponseVO<>(State.ERROR);
    }

    public static ResponseVO error(Object data) {
        return new ResponseVO<>(State.ERROR, data);
    }

    public static ResponseVO error(State state) {
        return new ResponseVO<>(state);
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
