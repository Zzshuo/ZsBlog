package com.zs.blog.object;

import lombok.Data;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
@Data
public class ResponseVO<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseVO(ErrorEnum state) {
        this.code = state.getCode();
        this.message = state.getMessage();
    }

    public ResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVO(ErrorEnum state, T data) {
        this.code = state.getCode();
        this.message = state.getMessage();
        this.data = data;
    }

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
