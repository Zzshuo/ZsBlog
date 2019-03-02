package com.zs.blog.mapper;

import com.zs.blog.model.ArticleTag;
import com.zs.blog.model.ArticleTagExample;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by Mybatis Generator on 2019/03/02
*/
public interface ArticleTagMapper extends Mapper<ArticleTag> {
    long countByExample(ArticleTagExample example);
}