package com.zs.blog.mapper;

import com.zs.blog.model.Type;
import com.zs.blog.model.TypeExample;
import tk.mybatis.mapper.common.Mapper;

public interface TypeMapper extends Mapper<Type> {
    long countByExample(TypeExample example);
}