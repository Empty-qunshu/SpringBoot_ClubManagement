package com.example.club.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 通知公告表 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    // 公告ID
    private Integer id;
    // 标题
    private String title;
    // 内容
    private String content;
    // 发布人ID
    private Integer publisherId;
    // 发布时间
    private LocalDateTime createTime;
}