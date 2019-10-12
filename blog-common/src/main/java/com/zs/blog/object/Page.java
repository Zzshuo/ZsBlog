package com.zs.blog.object;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zshuo
 * @date 2019-09-30
 **/
@Data
public class Page<T> implements Serializable {

    /**
     * 结果集
     */
    private List<T> list;
    /**
     * 当前页
     */
    private int pageNo;
    /**
     * 每页显示记录数
     */
    private int pageSize;
    /**
     * 总数
     */
    private long totalCount;
    /**
     * 总页数
     */
    private long totalPage;
}
