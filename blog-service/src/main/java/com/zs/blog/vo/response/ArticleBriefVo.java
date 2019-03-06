package com.zs.blog.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * @author zshuo
 * @date 2019/2/28
 **/
@Data
public class ArticleBriefVo {
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
}
