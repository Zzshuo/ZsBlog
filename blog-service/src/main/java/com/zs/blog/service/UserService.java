package com.zs.blog.service;

import com.github.pagehelper.PageInfo;
import com.zs.blog.model.User;
import com.zs.blog.vo.request.UserReqVo;

/**
 * @author zshuo
 * @date 2018/10/10
 **/
public interface UserService {

    User getUserById(Integer id);

    User getByUserName(String username);

    PageInfo<User> getUsers(UserReqVo userReqVo);
}
