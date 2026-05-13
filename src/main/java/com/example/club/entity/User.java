package com.example.club.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // 用户ID
    private Integer id;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 真实姓名
    private String realName;
    // 学号
    private String studentNo;
    // 性别
    private String gender;
    // 电话
    private String phone;
    // 邮箱
    private String email;
    // 角色：1.学生 2.社团管理员 3.管理员
    private Integer role;
    // 头像地址
    private String avatar;
    // 状态：1正常 0停用
    private Integer status;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
}