package com.zs.blog.mapper;

import com.zs.blog.model.Config;
import com.zs.blog.model.ConfigExample;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by Mybatis Generator on 2019/04/23
*/
public interface ConfigMapper extends Mapper<Config> {
    long countByExample(ConfigExample example);
}