package com.zs.blog.mapper;

import com.zs.blog.model.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zshuo
 * @date 2019/3/7
 **/
public interface SelfMapper {

    /**
     * 根据articleId 获取文章标签id
     *
     * @param articleId
     * @return
     */
    @Select("SELECT tag_id FROM article_tag WHERE article_id = #{articleId}")
    List<Integer> getTagIdListByArticleId(@Param("articleId") Integer articleId);

    @Select("SELECT * FROM article where id in (SELECT article_id FROM article_tag WHERE tag_id = #{tagId})")
    List<Article> getArticleByTagId(@Param("articleId") Integer tagId);
}
