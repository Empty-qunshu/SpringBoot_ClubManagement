package com.example.club.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动表 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    // 活动ID
    private Integer id;
    // 社团ID
    private Integer clubId;
    // 活动名称
    private String activityName;
    // 活动描述
    private String description;
    // 活动地点
    private String location;
    // 开始时间
    private LocalDateTime startTime;
    // 结束时间
    private LocalDateTime endTime;
    // 最大人数
    private Integer maxPeople;
    // 当前人数
    private Integer currentPeople;
    // 发布人ID
    private Integer publisherId;
    // 状态：1未开始 2进行中 3已结束
    private Integer status;
    // 创建时间
    private LocalDateTime createTime;
}