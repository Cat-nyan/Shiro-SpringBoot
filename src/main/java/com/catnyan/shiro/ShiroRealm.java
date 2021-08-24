package com.catnyan.shiro;

import com.catnyan.entity.User;
import com.catnyan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cat-Nyan
 * on 2021/8/23 12:44
 */
@Component
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        //设置角色
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        //设置权限
        info.addStringPermission(user.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //authenticationToken将由你提交的表单生成，登录信息全部被封装在token中
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByUsername(token.getUsername());
        if (user != null) {
            //密码不正确将会抛出IncorrectCredentialsException
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        }
        //未找到用户记录，将会抛出UnknownAccountException
        return null;
    }
}
