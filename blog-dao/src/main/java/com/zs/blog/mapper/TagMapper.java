package com.zs.blog.mapper;

import com.zs.blog.model.Tag;
import com.zs.blog.model.TagExample;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by Mybatis Generator
*/
public interface TagMapper extends Mapper<Tag> {
    long countByExample(TagExample example);
}