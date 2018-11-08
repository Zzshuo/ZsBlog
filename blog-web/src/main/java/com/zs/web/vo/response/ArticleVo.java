package com.zs.web.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * @author zshuo
 * @date 2018/11/8
 **/
@Data
public class ArticleVo {

    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否原创
     */
    private Byte original;

    /**
     * 类型
     */
    private Byte typeId;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 文章简介，最多200字
     */
    private String description;

    /**
     * 文章关键字，优化搜索
     */
    private String keywords;

    /**
     * 1:正常,2:删除
     */
    private Byte state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 文章内容
     */
    private String content;

}
