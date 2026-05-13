package com.example.club.service;

import com.example.club.entity.ActivitySignup;
import com.example.club.mapper.ActivityMapper;
import com.example.club.mapper.ActivitySignupMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivitySignupService {

    private final ActivitySignupMapper signupMapper;
    private final ActivityMapper activityMapper;

    public ActivitySignupService(ActivitySignupMapper signupMapper, ActivityMapper activityMapper) {
        this.signupMapper = signupMapper;
        this.activityMapper = activityMapper;
    }

    public List<ActivitySignup> findAll() {
        return signupMapper.selectAll();
    }

    @Transactional
    public ActivitySignup join(ActivitySignup signup) {
        signup.setSignupTime(LocalDateTime.now());
        signup.setSignStatus(0);
        signup.setStatus(1);
        signupMapper.insert(signup);
        activityMapper.increaseCurrentPeople(signup.getActivityId());
        return signup;
    }

    @Transactional
    public void cancel(Integer id) {
        ActivitySignup signup = signupMapper.selectById(id);
        if (signup == null || Integer.valueOf(0).equals(signup.getStatus())) {
            return;
        }
        signup.setStatus(0);
        signupMapper.update(signup);
        activityMapper.decreaseCurrentPeople(signup.getActivityId());
    }

    public void checkin(Integer id) {
        ActivitySignup signup = signupMapper.selectById(id);
        if (signup == null) {
            return;
        }
        signup.setSignStatus(1);
        signupMapper.update(signup);
    }

    public List<ActivitySignup> findByUserId(Integer userId) {
        return signupMapper.selectByUserId(userId);
    }

    public List<ActivitySignup> findByActivityId(Integer activityId) {
        return signupMapper.selectByActivityId(activityId);
    }
}
