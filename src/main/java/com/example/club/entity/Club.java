package com.example.club.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 社团表 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    // 社团ID
    private Integer id;
    // 社团名称
    private String clubName;
    // 社团简介
    private String clubDescription;
    // 社团类型
    private String clubType;
    // 负责人ID
    private Integer leaderId;
    // 联系方式
    private String contactPhone;
    // 活动地点
    private String location;
    // 成员人数
    private Integer memberCount;
    // 状态：1正常 0停用
    private Integer status;
    // 创建时间
    private LocalDateTime createTime;
}
