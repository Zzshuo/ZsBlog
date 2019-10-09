package com.zs.blog.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

/**
 * @author zshuo
 * @date 2019-09-30
 **/
public class BeanUtil {

    public static <T> T copy(Object source, Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();
            copy(source, target);

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return target;
    }

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
