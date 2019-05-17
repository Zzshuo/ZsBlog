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

    USER_LOGOUT(50008, "请重新登录！"),

    /**
     * 文章
     */
    ARTICLE_NOT_EXIST(50100, "文章不存在！"),
    /**
     * 标签
     */
    TAG_NOT_EXIST(50200, "标签不存在！"),
    TAG_EXIST(50201, "[{0}]标签已存在"),
    TAG_IN_USE(50202, "标签使用中,不能删除！"),

    /**
     * 分类
     */
    TYPE_NOT_EXIST(50200, "分类不存在！"),
    TYPE_EXIST(50201, "[{0}]分类已存在"),
    TYPE_IN_USE(50202, "分类使用中,不能删除！"),
    ;

    private Integer code;
    private String message;
}
