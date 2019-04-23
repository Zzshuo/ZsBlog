package com.zs.blog.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

public class BeanUtil {
    public static void copy(Object source, Object target) {
        copy(source, target, null);
    }

    public static void copy(Object source, Object target, Converter converter) {
        BeanCopier beanCopier;
        if (converter == null) {
            beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
        } else {
            beanCopier = BeanCopier.create(source.getClass(), target.getClass(), true);
        }
        beanCopier.copy(source, target, converter);
    }
}
