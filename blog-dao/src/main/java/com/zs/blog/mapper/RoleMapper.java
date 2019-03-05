package com.zs.blog.mapper;

import com.zs.blog.model.Role;
import com.zs.blog.model.RoleExample;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by Mybatis Generator on 2019/03/05
*/
public interface RoleMapper extends Mapper<Role> {
    long countByExample(RoleExample example);
}