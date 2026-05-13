package com.example.club.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 社团报名表 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubApply {
    // 报名ID
    private Integer id;
    // 学生ID
    private Integer userId;
    // 社团ID
    private Integer clubId;
    // 招新ID
    private Integer recruitmentId;
    // 报名理由
    private String applyReason;
    // 个人优势
    private String personalStrength;
    // 状态：0待审核 1通过 2拒绝
    private Integer status;
    // 报名时间
    private LocalDateTime applyTime;
    // 审核时间
    private LocalDateTime reviewTime;
}