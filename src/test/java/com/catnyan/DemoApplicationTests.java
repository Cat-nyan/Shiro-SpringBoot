package com.catnyan;

import com.catnyan.entity.User;
import com.catnyan.mapper.UserMapper;
import com.catnyan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @Test
    void mapper() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void service() {
        User user = userService.findByUsername("zs");
        System.out.println(user);
    }

}
