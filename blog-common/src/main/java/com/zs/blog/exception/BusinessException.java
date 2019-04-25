package com.zs.blog.exception;

import com.zs.blog.enums.ResponseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zshuo
 * @date 2018/11/9
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private ResponseEnum responseEnum;
    private Object[] params;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public BusinessException(ResponseEnum responseEnum, Object[] params) {
        this.responseEnum = responseEnum;
        this.params = params;
    }
}
