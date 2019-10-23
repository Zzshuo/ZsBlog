package com.zs.blog.vo.response;

import lombok.Data;

/**
 * @author zshuo
 * @date 2019-05-08
 **/
@Data
public class TypeDetailVo {
    private Integer id;

    /**
     * 分类
     */
    private String name;

    /**
     * 数量
     */
    private Integer count;
}
