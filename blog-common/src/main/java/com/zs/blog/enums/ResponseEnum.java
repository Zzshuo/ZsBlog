package com.zs.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zshuo
 * @date 2018/10/8
 **/
@AllArgsConstructor
@Getter
public enum ResponseEnum {

    SUCCESS(20000, "操作成功！"),
    ERROR(50000, "系统错误！"),

    ERROR_NO_ARTICLE(50100, "没有该文章！"),
    ERROR_NO_TAG(50101, "没有该标签！"),
    ERROR_EXIST_TAG(50102, "{}标签已存在"),
    ERROR_TAG_USED(50103, "标签使用中,不能删除！"),
    ;

    private Integer code;
    private String message;
}
