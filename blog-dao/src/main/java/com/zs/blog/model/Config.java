package com.zs.blog.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator
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
    @Column(name = "config_key")
    private String configKey;

    /**
     * 名称
     */
    @Column(name = "config_name")
    private String configName;

    /**
     * 值
     */
    @Column(name = "config_value")
    private String configValue;

    /**
     * 分类
     */
    @Column(name = "config_type")
    private Integer configType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}