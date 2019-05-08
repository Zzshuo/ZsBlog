package com.zs.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zshuo
 * @date 2019/4/9
 **/
public class ArticleEnum {

    @AllArgsConstructor
    @Getter
    public enum StateEnum {
        PUBLISH(1, "发布"),
        DRAFT(2, "草稿"),
        DELETE(0, "删除"),
        ;
        private Integer id;
        private String name;
    }

}
