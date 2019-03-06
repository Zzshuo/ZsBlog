package com.zs.blog.vo.request;

import lombok.Data;

/**
 * @author zshuo
 * @date 2019/3/6
 **/
@Data
public class TagReqVo {
    private Integer id;

    /**
     * 标签名
     */
    private String name;
}
