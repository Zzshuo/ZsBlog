package com.zs.blog.exception;

import com.zs.blog.enums.ResponseEnum;
import com.zs.blog.object.ResponseVo;
import com.zs.blog.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

/**
 * @author zshuo
 * @date 2018/11/9
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseVo errorHandler(Exception ex) {
        log.error("全局异常", ex);
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
    public ResponseVo errorHandler(BusinessException ex) {
        ResponseEnum responseEnum = ex.getResponseEnum();
        if (Objects.nonNull(responseEnum)) {
            String message = responseEnum.getMessage();
            if (Objects.nonNull(ex.getParams())) {
                message = MessageFormat.format(message, ex.getParams());
            }
            log.info("error code:{},{}", responseEnum.getCode(), message);
            return ResponseUtil.error(responseEnum.getCode(), message);
        }
        log.info("error code:{},{}", ResponseEnum.ERROR.getCode(), ex.getMessage());
        return ResponseUtil.error(ex.getMessage());
    }

//    @Valid	            被注释的元素是一个对象，需要检查此对象的所有字段值
//    @Null	                被注释的元素必须为 null
//    @NotNull              被注释的元素必须不为 null
//    @AssertTrue	        被注释的元素必须为 true
//    @AssertFalse	        被注释的元素必须为 false
//    @Min(value)	        被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//    @Max(value)	        被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//    @DecimalMin(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//    @DecimalMax(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//    @Size(max, min)	    被注释的元素的大小必须在指定的范围内
//    @Digits (integer, fraction)	被注释的元素必须是一个数字，其值必须在可接受的范围内
//    @Past	                被注释的元素必须是一个过去的日期
//    @Future	            被注释的元素必须是一个将来的日期
//    @Pattern(value)	    被注释的元素必须符合指定的正则表达式
//    @Email	            被注释的元素必须是电子邮箱地址
//    @Length(min=, max=)	被注释的字符串的大小必须在指定的范围内
//    @NotEmpty	            被注释的字符串的必须非空
//    @Range(min=, max=)	被注释的元素必须在合适的范围内
//    @NotBlank	            被注释的字符串的必须非空
//    @URL(protocol=, host=, port=, regexp=, flags=)	被注释的字符串必须是一个有效的url
//    @CreditCardNumber     被注释的字符串必须通过Luhn校验算法，银行卡，信用卡等号码一般都用Luhn 计算合法性

    /**
     * 对象参数校验
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVo errorHandler(MethodArgumentNotValidException ex) {
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (CollectionUtils.isEmpty(objectErrors)) {
            return ResponseUtil.error(ex.getMessage());
        }
        StringBuilder msgBuilder = new StringBuilder();
        for (ObjectError objectError : objectErrors) {
            msgBuilder
                    .append(((FieldError) objectError).getField())
                    .append(":")
                    .append(objectError.getDefaultMessage())
                    .append(",");
        }
        String message = msgBuilder.toString();
        if (message.length() > 1) {
            message = message.substring(0, message.length() - 1);
        }
        return ResponseUtil.error(message);
    }

}
