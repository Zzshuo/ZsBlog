package com.zs.blog.object;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zshuo
 * @date 2018/10/11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestVo extends BaseReqVo {
    public final static int DEFAULT_PAGE_NUM = 1;
    public final static int DEFAULT_PAGE_SIZE = 10;
    private Integer pageNum;
    private Integer pageSize;

    public int getPageNum() {
        return pageNum == null ? DEFAULT_PAGE_NUM : pageNum;
    }

    public int getPageSize() {
        return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
