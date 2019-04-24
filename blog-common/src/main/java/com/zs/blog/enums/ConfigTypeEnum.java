package com.zs.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author zshuo
 * @date 2019-04-24
 **/
@AllArgsConstructor
@Getter
public enum ConfigTypeEnum {

    COMMON(1, "默认"),
    ;

    private Integer id;
    private String name;

    public static ConfigTypeEnum getEnumById(Integer id) {
        if (Objects.isNull(id)) {
            return null;
        }
        for (ConfigTypeEnum configTypeEnum : ConfigTypeEnum.values()) {
            if (Objects.equals(configTypeEnum.getId(), id)) {
                return configTypeEnum;
            }
        }
        return null;
    }
}
