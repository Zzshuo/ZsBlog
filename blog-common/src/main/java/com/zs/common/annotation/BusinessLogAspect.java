package com.zs.common.annotation;

import com.zs.common.util.AspectUtil;
import com.zs.common.util.RegexUtil;
import com.zs.common.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Auther zshuo
 * @Date 2018/9/30
 **/
@Slf4j
@Aspect
@Component
public class BusinessLogAspect {
    @Pointcut(value = "@annotation(com.zs.common.annotation.BusinessLog)")
    public void pointcut() {
    }

    public Object writeLog(ProceedingJoinPoint point) throws Throwable {
        //先执行业务
        Object result = point.proceed();

        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
        log.error("捕获到了异常...", ex);
    }

    private void handle(ProceedingJoinPoint point) throws Exception {
        //获取拦截方法的参数
        String className = AspectUtil.getClassName(point);
        Method currentMethod = AspectUtil.getMethod(point);
        //获取操作名称
        BusinessLog annotation = currentMethod.getAnnotation(BusinessLog.class);
        String bussinessName = parseContent(point.getArgs(), annotation.value());
        String ua = RequestUtil.getUa();

        log.info("{}-{}.{}", bussinessName, className, currentMethod.getName());
        log.info("IP: {}, Method: {}, Request URL: {}", RequestUtil.getIp(), RequestUtil.getMethod(), RequestUtil.getRequestUrl());
        log.info("User-Agent: " + ua);
    }

    private String parseContent(Object[] params, String bussinessName) {
        if (bussinessName.contains("{") && bussinessName.contains("}")) {
            List<String> result = RegexUtil.match(bussinessName, "(?<=\\{)(\\d+)");
            for (String s : result) {
                int index = Integer.parseInt(s);
                bussinessName = bussinessName.replaceAll("\\{" + index + "\\}", String.valueOf(params[index - 1]));
            }
        }
        return bussinessName;
    }
}
