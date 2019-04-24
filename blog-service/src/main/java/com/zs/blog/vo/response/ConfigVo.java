package com.zs.blog.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class ConfigVo {
    private Integer id;

    /**
     * key
     */
    private String configKey;

    /**
     * 名称
     */
    private String configName;

    /**
     * 值
     */
    private String configValue;

    /**
     * 分类
     */
    private Integer configType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
