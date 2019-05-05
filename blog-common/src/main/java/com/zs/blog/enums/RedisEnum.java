package com.zs.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zshuo
 * @date 2019-05-05
 **/
@AllArgsConstructor
@Getter
public enum RedisEnum {

    CONFIG("CONFIG"),
    ;

    private String redisKey;

    private static final String reids_prefix = "zshuo";

    public String getRedisKey() {
        return reids_prefix + redisKey;
    }
}
