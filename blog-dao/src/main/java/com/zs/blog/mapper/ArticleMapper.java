package com.zs.blog.mapper;

import com.zs.blog.model.Article;
import com.zs.blog.model.ArticleExample;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by Mybatis Generator on 2019/04/23
*/
public interface ArticleMapper extends Mapper<Article> {
    long countByExample(ArticleExample example);
}