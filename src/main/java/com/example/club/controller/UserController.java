package com.example.club.controller;

import com.example.club.entity.PageResult;
import com.example.club.entity.Result;
import com.example.club.entity.User;
import com.example.club.service.UserService;
import com.example.club.util.JwtUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        User result = userService.login(user.getUsername(), user.getPassword());
        Map<String, Object> res = new HashMap<>();
        if (result == null) {
            res.put("code", 500);
            res.put("message", "用户名或密码错误");
        } else {
            res.put("code", 200);
            res.put("message", "登录成功");
            res.put("data", userService.withoutPassword(result));
            res.put("token", JwtUtil.generate(result));
        }
        return res;
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (user == null || user.getUsername() == null || user.getUsername().isBlank()
                || user.getPassword() == null || user.getPassword().isBlank()) {
            return Result.error("用户名和密码不能为空");
        }
        User exist = userService.findByUsername(user.getUsername());
        if (exist != null) {
            return Result.error("用户名已存在");
        }
        userService.register(user);
        return Result.success(userService.withoutPassword(user));
    }

    @GetMapping("/list")
    public Result list(@RequestParam(required = false) Integer role,
                       @RequestParam(required = false) String keyword,
                       @RequestParam(required = false) Integer page,
                       @RequestParam(required = false) Integer pageSize) {
        List<User> users = userService.withoutPasswords(userService.search(role, keyword));
        if (page != null || pageSize != null) {
            return Result.success(PageResult.of(users, page, pageSize));
        }
        return Result.success(users);
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id) {
        User user = userService.findById(id);
        return user != null ? Result.success(userService.withoutPassword(user)) : Result.error("用户不存在");
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @PutMapping("/status/{id}")
    public Result updateStatus(@PathVariable Integer id, @RequestParam Integer status) {
        userService.updateStatus(id, status);
        return Result.success();
    }

    @PutMapping("/password/{id}")
    public Result updatePassword(@PathVariable Integer id, @RequestParam String password) {
        userService.updatePassword(id, password);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/byRole")
    public Result getByRole(@RequestParam Integer role) {
        return Result.success(userService.withoutPasswords(userService.findByRole(role)));
    }
}
