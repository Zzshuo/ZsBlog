package com.zs.blog.service.impl;

import com.zs.blog.mapper.RoleMapper;
import com.zs.blog.model.Role;
import com.zs.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zshuo
 * @date 2018/10/10
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
