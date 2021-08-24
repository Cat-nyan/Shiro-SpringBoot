package com.catnyan.entity;

import lombok.Data;

/**
 * Created by Cat-Nyan
 * on 2021/8/23 12:53
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String perms;
    private String role;
}
