package com.zs.blog.object;

import java.util.Objects;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
public enum ErrorEnum {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功！"),
    ERROR(500, "服务器未知错误！"),

    ERROR_NO_ARTICLE(500, "没有该文章！")

    //
    ;

    private Integer code;
    private String message;

    ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorEnum getResponseStatus(String message) {
        for (ErrorEnum s : ErrorEnum.values()) {
            if (Objects.equals(s.getMessage(), message)) {
                return s;
            }
        }
        return null;
    }

}
