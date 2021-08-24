package com.catnyan.controller;

import com.catnyan.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Cat-Nyan
 * on 2021/8/23 14:52
 */
@Controller
public class UserController {

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            subject.getSession().setAttribute("user", user);
            return "/index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("msg", "用户不存在！");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("msg", "密码错误！");
        }
        return null;
    }

    /**
     * 没有权限时将直接显示错误信息：”未授权，无法访问“
     */
    @GetMapping("/unauthc")
    @ResponseBody
    public String unauthc() {
        return "未授权，无法访问";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //logout方法将会移除session、principals、authenticated
        subject.logout();
        return "/login";
    }
}
