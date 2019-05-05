package com.zs.blog.object;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zshuo
 * @date 2019-05-05
 **/
@Data
public class PageInfo<T> implements Serializable {

    /**
     * 结果集
     */
    private List<T> list;
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 每页显示记录数
     */
    private int pageSize;
    /**
     * 总数
     */
    private long total;

    public PageInfo(List<T> list, Page page) {
        this.list = list;
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getTotal();
    }

    public PageInfo(List<T> list, List pageList) {
        Page page = (Page) pageList;
        this.list = list;
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getTotal();
    }
}
