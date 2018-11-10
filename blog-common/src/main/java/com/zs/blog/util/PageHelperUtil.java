package com.zs.blog.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zs.blog.object.RequestVo;

import java.util.List;

/**
 * pageHelper 分页工具类
 *
 * @author zshuo
 * @date 2018/11/8
 **/
public class PageHelperUtil {

    /**
     * 执行sql前执行
     * (与sql语句之间不能有其他语句,否则可能导致分页不安全)
     *
     * @param vo
     * @return
     */
    public static Page<Object> startPage(RequestVo vo) {
        return PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
    }

    /**
     * 对结果进行封装
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> result(List source, Page<T> target) {
        Page source1 = (Page) source;
        target.setPageNum(source1.getPageNum());
        target.setPageSize(source1.getPageSize());
        target.setStartRow(source1.getStartRow());
        target.setEndRow(source1.getEndRow());
        target.setTotal(source1.getTotal());
        target.setPages(source1.getPages());
        target.setCount(source1.isCount());
        target.setReasonable(source1.getReasonable());
        target.setPageSizeZero(source1.getPageSizeZero());
        target.setCountColumn(source1.getCountColumn());
        target.setOrderBy(source1.getOrderBy());
        target.setOrderByOnly(source1.isOrderByOnly());
        return new PageInfo<>(target);
    }
}
