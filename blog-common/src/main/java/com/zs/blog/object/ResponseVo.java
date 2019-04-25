package com.zs.blog.object;

import com.zs.blog.enums.ResponseEnum;
import lombok.Data;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
@Data
public class ResponseVo<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseVo(ResponseEnum state) {
        this.code = state.getCode();
        this.message = state.getMessage();
    }

    public ResponseVo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVo(ResponseEnum state, T data) {
        this.code = state.getCode();
        this.message = state.getMessage();
        this.data = data;
    }

    public ResponseVo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
