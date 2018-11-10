package com.zs.blog.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessLog {

    /**
     * 业务流程
     *
     * @return
     */
    String value() default "";
}
