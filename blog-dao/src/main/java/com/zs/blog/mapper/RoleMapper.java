package com.zs.blog.mapper;

import com.zs.blog.model.Role;
import com.zs.blog.model.RoleExample;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by Mybatis Generator
*/
public interface RoleMapper extends Mapper<Role> {
    long countByExample(RoleExample example);
}