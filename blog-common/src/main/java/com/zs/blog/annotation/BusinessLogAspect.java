package com.zs.blog.annotation;

import com.alibaba.fastjson.JSON;
import com.zs.blog.util.AspectUtil;
import com.zs.blog.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author zshuo
 * @Date 2018/9/30
 **/
@Slf4j
@Aspect
@Component
public class BusinessLogAspect {

    //    @Pointcut(value = "@annotation(com.zs.blog.annotation.BusinessLog)")
    @Pointcut("execution(* com.zs.blog.controller..*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint point) throws NoSuchMethodException {
        //获取拦截方法的参数
        String className = AspectUtil.getClassName(point);
        Method method = AspectUtil.getMethod(point);
        //获取操作名称
        String value = getBusinessName(method);
        String ua = RequestUtil.getUa();

        log.info("{}: {}.{} \r\n Request URL: {} ,IP: {}, Method: {}, Params: {} \r\n User-Agent: {}", value, className, method.getName(), RequestUtil.getRequestUrl(), RequestUtil.getIp(), RequestUtil.getMethod(), JSON.toJSONString(point.getArgs()), ua);
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void after(JoinPoint point, Object result) throws NoSuchMethodException {
        Method method = AspectUtil.getMethod(point);
        String value = getBusinessName(method);
        log.info("{} return: {}", value, JSON.toJSONString(result));
    }

    private String getBusinessName(Method method) throws NoSuchMethodException {
        BusinessLog annotation = method.getAnnotation(BusinessLog.class);
        String value;
        if (annotation != null) {
            value = annotation.value();
        } else {
            value = method.getName();
        }
        return value;
    }
}
