package com.zs.blog.mapper;

import com.zs.blog.model.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zshuo
 * @date 2019/3/7
 **/
public interface SelfMapper {

    /**
     * 根据articleId 获取文章标签
     *
     * @param articleId
     * @return
     */
    @Select("SELECT * FROM tag WHERE id IN (SELECT tag_id FROM article_tag WHERE article_id = #{articleId})")
    List<Tag> getTagByArticleId(@Param("ArticleId") Integer articleId);
}
