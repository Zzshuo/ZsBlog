package com.zs.dao.mapper;

import com.zs.dao.model.Article;
import com.zs.dao.model.ArticleExample;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleMapper extends Mapper<Article> {
    long countByExample(ArticleExample example);
}