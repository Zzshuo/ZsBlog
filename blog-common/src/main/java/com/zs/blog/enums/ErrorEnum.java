package com.zs.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
@AllArgsConstructor
@Getter
public enum ErrorEnum {

    SUCCESS(200, "操作成功！"),
    ERROR(500, "系统错误！"),

    ERROR_NO_ARTICLE(500, "没有该文章！"),
    ERROR_NO_TAG(501, "没有该标签！");

    private Integer code;
    private String message;
}
