package com.zs.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zs.blog.mapper.UserMapper;
import com.zs.blog.model.User;
import com.zs.blog.model.UserExample;
import com.zs.blog.service.UserService;
import com.zs.blog.vo.request.UserReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zshuo
 * @date 2018/10/11
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getByUserName(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public PageInfo<User> getUsers(UserReqVo userReqVo) {
        UserExample example = new UserExample();
        PageHelper.startPage(userReqVo.getPageNum(), userReqVo.getPageSize());
        List<User> users = userMapper.selectByExample(example);
        return new PageInfo<>(users);
    }
}
