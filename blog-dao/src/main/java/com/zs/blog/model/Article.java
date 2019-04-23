package com.zs.blog.model;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/04/23
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
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
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 文章封面图片
     */
    private String image;

    /**
     * 1:正常,2:删除,3:草稿
     */
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 文章内容
     */
    private String content;
}