package com.zs.blog.util;

import com.zs.blog.enums.ResponseEnum;
import com.zs.blog.object.ResponseVo;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
public class ResponseUtil {

    public static ResponseVo success() {
        return new ResponseVo<>(ResponseEnum.SUCCESS);
    }

    public static ResponseVo success(Object data) {
        return new ResponseVo<>(ResponseEnum.SUCCESS, data);
    }

    public static ResponseVo error() {
        return new ResponseVo<>(ResponseEnum.ERROR);
    }

    public static ResponseVo error(String message) {
        return new ResponseVo<>(ResponseEnum.ERROR.getCode(), message);
    }

    public static ResponseVo error(ResponseEnum state) {
        return new ResponseVo<>(state);
    }

    public static ResponseVo error(Integer code, String message) {
        return new ResponseVo<>(code, message);
    }
}
