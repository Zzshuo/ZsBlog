package com.zs.blog.mapper;

import com.zs.blog.model.Type;
import com.zs.blog.model.TypeExample;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by Mybatis Generator on 2019/04/23
*/
public interface TypeMapper extends Mapper<Type> {
    long countByExample(TypeExample example);
}