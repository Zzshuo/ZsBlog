package com.zs.dao.mapper;

import com.zs.dao.model.User;
import com.zs.dao.model.UserExample;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    long countByExample(UserExample example);
}