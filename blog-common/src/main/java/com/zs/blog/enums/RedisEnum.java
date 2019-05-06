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

    REDIS_CACHE_PREFIX("redis_cache_", "redis 缓存"),
    CONFIG("config", "配置缓存"),
    ;

    private String redisKey;
    private String name;

    private static final String REDIS_PREFIX = "zshuo_";

    public String getRedisKey() {
        return REDIS_PREFIX + redisKey;
    }
}
