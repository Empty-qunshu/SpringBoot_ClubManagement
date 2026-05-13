package com.example.club.controller;

import com.example.club.entity.LoginInfo;
import com.example.club.entity.Result;
import com.example.club.entity.User;
import com.example.club.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("login:{}", user == null ? null : user.getUsername());
        LoginInfo info = userService.login(user);
        return info != null ? Result.success(info) : Result.error("用户名或密码错误");
    }
}
