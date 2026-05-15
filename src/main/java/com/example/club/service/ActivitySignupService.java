package com.example.club.service;

import com.example.club.entity.ActivitySignup;
import com.example.club.mapper.ActivityMapper;
import com.example.club.mapper.ActivitySignupMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

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

    public List<ActivitySignup> search(Integer status, String keyword) {
        List<ActivitySignup> signups = signupMapper.selectAll();
        if (status != null) {
            signups = signups.stream().filter(signup -> status.equals(signup.getStatus())).toList();
        }
        if (!StringUtils.hasText(keyword)) return signups;
        String kw = keyword.toLowerCase(Locale.ROOT);
        return signups.stream()
                .filter(signup -> contains(signup.getId(), kw)
                        || contains(signup.getActivityId(), kw)
                        || contains(signup.getUserId(), kw)
                        || contains(signup.getSignStatus(), kw))
                .toList();
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

    private boolean contains(Object value, String keyword) {
        return value != null && String.valueOf(value).toLowerCase(Locale.ROOT).contains(keyword);
    }
}
