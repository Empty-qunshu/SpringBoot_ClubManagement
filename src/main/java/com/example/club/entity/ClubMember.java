package com.example.club.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 社团成员表 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubMember {
    // 成员ID
    private Integer id;
    // 社团ID
    private Integer clubId;
    // 用户ID
    private Integer userId;
    // 成员角色：1普通成员 2干部 3社长
    private Integer memberRole;
    // 加入时间
    private LocalDateTime joinTime;
    // 状态：1在团 0退出
    private Integer status;
}