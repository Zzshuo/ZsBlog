package com.zs.blog.mapper;

import com.zs.blog.model.Tag;
import com.zs.blog.model.TagExample;
import tk.mybatis.mapper.common.Mapper;

public interface TagMapper extends Mapper<Tag> {
    long countByExample(TagExample example);
}