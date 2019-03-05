package com.zs.blog.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * @author zshuo
 * @date 2018/11/8
 **/
@Data
public class ArticleDetailVo {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否原创
     */
    private Boolean original;

    /**
     * 类型
     */
    private Integer typeId;

    /**
     * 文章封面图片
     */
    private String image;

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
