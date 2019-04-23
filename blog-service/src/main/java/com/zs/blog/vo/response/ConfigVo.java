package com.zs.blog.vo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfigVo {
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
