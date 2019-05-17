package com.zs.blog.config.shiro;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zshuo
 * @date 2019-05-15
 **/
@Data
@Component
public class ShiroProperties {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.expire}")
    private int expire;

    /**
     * 加密算法
     */
    private String hashAlgorithmName = "MD5";

    /**
     * 散列次数
     */
    private int hashIterations = 1024;
}
