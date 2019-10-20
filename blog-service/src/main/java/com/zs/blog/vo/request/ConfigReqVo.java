package com.zs.blog.vo.request;

import com.zs.blog.object.PageReqVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zshuo
 * @date 2019-05-08
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigReqVo extends PageReqVo {
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
