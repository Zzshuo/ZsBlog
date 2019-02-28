package com.zs.blog.mapper;

import com.zs.blog.model.ArticleTag;
import com.zs.blog.model.ArticleTagExample;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleTagMapper extends Mapper<ArticleTag> {
    long countByExample(ArticleTagExample example);
}