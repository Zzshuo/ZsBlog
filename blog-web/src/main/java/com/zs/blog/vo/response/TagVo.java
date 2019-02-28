package com.zs.blog.vo.response;

import lombok.Data;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@Data
public class TagVo {
    private Integer id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 数量
     */
    private Integer count;
}
