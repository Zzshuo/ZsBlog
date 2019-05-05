package com.zs.blog.services;

import com.zs.blog.enums.RedisEnum;
import com.zs.blog.service.ConfigService;
import com.zs.blog.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zshuo
 * @date 2019-05-05
 **/
@Service
public class RedisService {

    @Autowired
    private ConfigService configService;

    @Autowired
    private RedisUtil redisUtil;

    public String getConfigValueByConfigKey(String item) {
        String redisKey = RedisEnum.CONFIG.getRedisKey();
        String value = redisUtil.hgetAsString(redisKey, item);
        if (value == null) {
            value = configService.getConfigValueByKey(item);
            redisUtil.hset(redisKey, item, value);
        }
        return value;
    }
}
