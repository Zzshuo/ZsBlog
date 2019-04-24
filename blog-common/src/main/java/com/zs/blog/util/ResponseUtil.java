package com.zs.blog.util;

import com.zs.blog.enums.ErrorEnum;
import com.zs.blog.object.ResponseVo;

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

    public static ResponseVo error(String message) {
        return new ResponseVo<>(ErrorEnum.ERROR.getCode(), message);
    }

    public static ResponseVo error(ErrorEnum state) {
        return new ResponseVo<>(state);
    }

    public static ResponseVo error(Integer code, String message) {
        return new ResponseVo<>(code, message);
    }
}
