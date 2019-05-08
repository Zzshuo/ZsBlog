package com.zs.blog.vo.response;

import lombok.Data;

import java.util.Date;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
