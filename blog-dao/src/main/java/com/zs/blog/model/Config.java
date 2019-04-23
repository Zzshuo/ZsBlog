package com.zs.blog.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/04/23
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    @Id
    private Integer id;

    /**
     * key
     */
    private String key;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    /**
     * 分类
     */
    private Integer type;
}