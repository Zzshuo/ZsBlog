package com.zs.blog.vo.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

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
     * 分类
     */
    private Integer typeId;

    /**
     * 标签id
     */
    private List<Integer> tagIdList;

    /**
     * 文章封面图片
     */
    private String image;

    /**
     * 1:发布,2:草稿,0:删除
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 评论次数
     */
    private Integer comments;
}
