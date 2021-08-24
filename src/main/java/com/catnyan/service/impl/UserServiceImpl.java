package com.catnyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.catnyan.entity.User;
import com.catnyan.mapper.UserMapper;
import com.catnyan.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Cat-Nyan
 * on 2021/8/23 13:17
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return  userMapper.selectOne(wrapper);
    }
}
