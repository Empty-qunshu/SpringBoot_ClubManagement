package com.example.club.service;

import com.example.club.entity.LoginInfo;
import com.example.club.entity.User;
import com.example.club.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User login(String username, String password) {
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) return null;
        User user = userMapper.findByUsername(username);
        if (user == null) return null;
        if (!Objects.equals(user.getPassword(), password)) return null;
        if (!Objects.equals(user.getStatus(), 1)) return null;
        return user;
    }

    public LoginInfo login(User user) {
        if (user == null) return null;
        User u = login(user.getUsername(), user.getPassword());
        if (u != null) {
            log.info("login success:{}", u.getUsername());
            return new LoginInfo(u.getId(), u.getUsername(), "");
        }
        return null;
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public List<User> findByRole(Integer role) {
        return userMapper.selectByRole(role);
    }

    public User withoutPassword(User user) {
        if (user == null) return null;
        User safeUser = new User();
        safeUser.setId(user.getId());
        safeUser.setUsername(user.getUsername());
        safeUser.setRealName(user.getRealName());
        safeUser.setStudentNo(user.getStudentNo());
        safeUser.setGender(user.getGender());
        safeUser.setPhone(user.getPhone());
        safeUser.setEmail(user.getEmail());
        safeUser.setRole(user.getRole());
        safeUser.setAvatar(user.getAvatar());
        safeUser.setStatus(user.getStatus());
        safeUser.setCreateTime(user.getCreateTime());
        safeUser.setUpdateTime(user.getUpdateTime());
        return safeUser;
    }

    public List<User> withoutPasswords(List<User> users) {
        List<User> safeUsers = new ArrayList<>();
        if (users == null) return safeUsers;
        for (User user : users) {
            safeUsers.add(withoutPassword(user));
        }
        return safeUsers;
    }

    public void register(User user) {
        user.setRole(1);
        user.setStatus(1);
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void updateStatus(Integer id, Integer status) {
        userMapper.updateStatus(id, status);
    }

    public void updatePassword(Integer id, String password) {
        userMapper.updatePassword(id, password);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }
}
