package com.zs.blog.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ConfigReqVo {
    private Integer id;

    /**
     * key
     */
    @NotBlank
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
    @NotNull
    private Integer configType;
}
