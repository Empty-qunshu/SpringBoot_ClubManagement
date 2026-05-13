package com.example.club.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动报名表 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivitySignup {
    // 报名ID
    private Integer id;
    // 活动ID
    private Integer activityId;
    // 学生ID
    private Integer userId;
    // 报名时间
    private LocalDateTime signupTime;
    // 签到状态：0未签到 1已签到
    private Integer signStatus;
    // 报名状态：1正常 0取消
    private Integer status;
}