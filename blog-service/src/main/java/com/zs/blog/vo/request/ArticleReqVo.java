package com.zs.blog.vo.request;

import lombok.Data;

import java.util.List;

/**
 * @author zshuo
 * @date 2019/3/6
 **/
@Data
public class ArticleReqVo {
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
     * 1:正常,2:删除,3:草稿
     */
    private Integer state;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<Integer> tagIdList;
}
