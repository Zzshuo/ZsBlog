package com.zs.web.service;

import com.zs.dao.model.Role;

import java.util.List;

/**
 * @author zshuo
 * @date 2018/10/11
 **/
public interface ShiroService {

    List<Role> getUserRolesByUserId(Integer userId);
}
