package com.example.club.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 招新表 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Recruitment {
    // 招新ID
    private Integer id;
    // 社团ID
    private Integer clubId;
    // 招新标题
    private String title;
    // 招新内容
    private String content;
    // 招新要求
    private String requirement;
    // 开始时间
    private LocalDateTime startTime;
    // 结束时间
    private LocalDateTime endTime;
    // 人数限制
    private Integer limitCount;
    // 当前报名人数
    private Integer currentCount;
    // 状态：1招新中 0已结束
    private Integer status;
    // 发布人ID
    private Integer publisherId;
    // 创建时间
    private LocalDateTime createTime;
}