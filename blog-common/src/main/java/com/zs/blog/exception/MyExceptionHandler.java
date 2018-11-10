package com.zs.blog.exception;

import com.zs.blog.object.ErrorEnum;
import com.zs.blog.object.ResponseVO;
import com.zs.blog.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * @author zshuo
 * @date 2018/11/9
 **/
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseVO errorHandler(Exception ex) {
        log.error(ex.getMessage());
        return ResponseUtil.error(ex.getMessage());
    }


    /**
     * 自定义异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseVO errorHandler(BusinessException ex) {
        ErrorEnum errorEnum = ex.getErrorEnum();
        if (Objects.nonNull(errorEnum)) {
            String message = errorEnum.getMessage();
            if (Objects.nonNull(ex.getParams())) {
                message = MessageFormat.format(message, ex.getParams());
            }
            log.error(message);
            return ResponseUtil.error(errorEnum.getCode(), message);
        }
        log.error(ex.getMessage());
        return ResponseUtil.error(ex.getMessage());
    }
}
