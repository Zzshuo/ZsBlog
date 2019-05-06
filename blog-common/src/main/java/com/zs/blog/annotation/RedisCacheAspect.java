package com.zs.blog.annotation;

import com.zs.blog.enums.RedisEnum;
import com.zs.blog.util.AspectUtil;
import com.zs.blog.util.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author zshuo
 * @Date 2018/9/30
 **/
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {
    private static final String CACHE_PREFIX = "cache_";

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut(value = "@annotation(com.zs.blog.annotation.RedisCache)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint point) throws Throwable {
        Method currentMethod = AspectUtil.getMethod(point);
        //获取操作名称
        RedisCache cache = currentMethod.getAnnotation(RedisCache.class);
        boolean flush = cache.flush();
        if (flush) {
            String classPrefix = AspectUtil.getKeyOfClassPrefix(point, RedisEnum.REDIS_CACHE_PREFIX.getRedisKey());
            log.info("清空缓存 - {}*", classPrefix);
            redisUtil.del(classPrefix);
            return point.proceed();
        }
        String key = AspectUtil.getKey(point, cache.key(), RedisEnum.REDIS_CACHE_PREFIX.getRedisKey());
        boolean hasKey = redisUtil.hasKey(key);
        if (hasKey) {
            try {
                log.info("{}从缓存中获取数据", key);
                return redisUtil.get(key);
            } catch (Exception e) {
                log.error("从缓存中获取数据失败！", e);
            }
        }
        //先执行业务
        Object result = point.proceed();
        redisUtil.set(key, result, cache.unit().toSeconds(cache.expire()));
        log.info("{}从数据库中获取数据", key);
        return result;
    }
}
