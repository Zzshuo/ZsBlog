package com.zs.web.service;

import com.github.pagehelper.PageInfo;
import com.zs.web.vo.UserVo;

/**
 * @author zshuo
 * @date 2018/10/10
 **/
public interface UserService {

    User getUserById(Integer id);

    User getByUserName(String username);

    PageInfo<User> getUsers(UserVo userVo);
}
