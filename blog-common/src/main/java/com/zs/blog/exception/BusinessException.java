package com.zs.blog.exception;

import com.zs.blog.enums.ErrorEnum;
import lombok.Data;

/**
 * @author zshuo
 * @date 2018/11/9
 **/
@Data
public class BusinessException extends RuntimeException {

    private ErrorEnum errorEnum;
    private Object[] params;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public BusinessException(ErrorEnum errorEnum, Object[] params) {
        this.errorEnum = errorEnum;
        this.params = params;
    }
}
