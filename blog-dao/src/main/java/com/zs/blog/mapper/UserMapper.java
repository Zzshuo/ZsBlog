package com.zs.blog.mapper;

import com.zs.blog.model.User;
import com.zs.blog.model.UserExample;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by Mybatis Generator on 2019/03/05
*/
public interface UserMapper extends Mapper<User> {
    long countByExample(UserExample example);
}