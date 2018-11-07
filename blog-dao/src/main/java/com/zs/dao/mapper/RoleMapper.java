package com.zs.dao.mapper;

import com.zs.dao.model.Role;
import com.zs.dao.model.RoleExample;
import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role> {
    long countByExample(RoleExample example);
}