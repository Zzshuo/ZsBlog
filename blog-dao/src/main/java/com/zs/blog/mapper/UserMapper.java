package com.zs.blog.mapper;

import com.zs.blog.model.User;
import com.zs.blog.model.UserExample;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    long countByExample(UserExample example);
}