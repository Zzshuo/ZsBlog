package com.zs.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ConfigEnum {
    ALI_OSS_END_POINT("ALI_OSS_END_POINT", "endpoint"),
    ALI_OSS_ACCESSKEY_ID("ALI_OSS_ACCESSKEY_ID", "AccessKey ID"),
    ALI_OSS_ACCESSKEY_SECRET("ALI_OSS_ACCESSKEY_SECRET", "AccessKeySecret"),
    ALI_OSS_BUCKET_NAME("ALI_OSS_BUCKET_NAME", "存储空间"),
    ;

    private String key;
    private String name;

    public static ConfigEnum getEnumByKey(String key) {
        if (StringUtils.isNotBlank(key)) {
            return null;
        }
        for (ConfigEnum configEnum : ConfigEnum.values()) {
            if (Objects.equals(configEnum.getKey(), key)) {
                return configEnum;
            }
        }
        return null;
    }
}
