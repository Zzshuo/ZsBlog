package com.zs.blog.object;

import lombok.Data;

/**
 * @author zshuo
 * @date 2018/10/11
 **/
@Data
public class RequestVo {
    public final static int DEFAULT_PAGE_SIZE = 10;
    private int pageNum = 1;
    private int pageSize = 0;

    public int getPageSize() {
        return pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }
}
