package com.zs.blog.util;

import com.zs.blog.object.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author zshuo
 * @date 2019-09-30
 **/
public class PageUtils {

    public static <T, R> Page<R> toPage(List<T> list, Function<T, R> mapper) {
        Page<R> page = new Page<>();
        if (list instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page<T> list1 = (com.github.pagehelper.Page<T>) list;
            page.setPageNum(list1.getPageNum());
            page.setPageSize(list1.getPageSize());
            page.setTotal(list1.getTotal());
            page.setPages(list1.getPages());
        }
        List<R> result = new ArrayList<>();
        list.forEach(item -> {
            result.add(mapper.apply(item));
        });
        page.setList(result);
        return page;
    }
}
