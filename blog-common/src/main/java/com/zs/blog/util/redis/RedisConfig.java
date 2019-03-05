package com.zs.blog.util.redis;

import com.alibaba.fastjson.parser.ParserConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zshuo
 * @date 2019/3/5
 **/
@Configuration
@Slf4j
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        // 设置AutoType 否则 FastJsonRedisSerializer  JSON.parseObject 会报错
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);

        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new FastJsonRedisSerializer(Object.class));
        log.info("RedisTemplate实例化成功！");
        return template;
    }
}
