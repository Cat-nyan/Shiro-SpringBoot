package com.catnyan.service;

import com.catnyan.entity.User;

/**
 * Created by Cat-Nyan
 * on 2021/8/23 13:16
 */
public interface UserService {
    public User findByUsername(String username);
}
