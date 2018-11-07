package com.zs.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zs.dao.mapper.UserMapper;
import com.zs.dao.model.User;
import com.zs.dao.model.UserExample;
import com.zs.web.service.UserService;
import com.zs.web.vo.UserVo;
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
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public PageInfo<User> getUsers(UserVo userVo) {
        UserExample example = new UserExample();
        PageHelper.startPage(userVo.getPageNumber(), userVo.getPageSize());
        List<User> users = userMapper.selectByExample(example);
        return new PageInfo<>(users);
    }
}
