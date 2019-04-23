package com.zs.blog.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ConfigReqVo {
    private Integer id;

    /**
     * key
     */
    @NotBlank
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
