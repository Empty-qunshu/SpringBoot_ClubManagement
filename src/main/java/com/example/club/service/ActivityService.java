package com.example.club.service;

import com.example.club.entity.Activity;
import com.example.club.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityMapper activityMapper;

    public ActivityService(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

    public List<Activity> findAll() {
        return activityMapper.selectAll();
    }

    public Activity findById(Integer id) {
        return activityMapper.selectById(id);
    }

    public Activity add(Activity activity) {
        activity.setCreateTime(LocalDateTime.now());
        activity.setCurrentPeople(0);
        activity.setStatus(1);
        activityMapper.insert(activity);
        return activity;
    }

    public void update(Activity activity) {
        activityMapper.update(activity);
    }

    public void updateStatus(Integer id, Integer status) {
        activityMapper.updateStatus(id, status);
    }

    public void deleteById(Integer id) {
        activityMapper.deleteById(id);
    }

    public List<Activity> findByClubId(Integer clubId) {
        return activityMapper.selectByClubId(clubId);
    }
}
