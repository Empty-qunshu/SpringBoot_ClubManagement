package com.example.club.service;

import com.example.club.mapper.ActivityMapper;
import com.example.club.mapper.ClubApplyMapper;
import com.example.club.mapper.ClubMapper;
import com.example.club.mapper.ClubMemberMapper;
import com.example.club.mapper.NoticeMapper;
import com.example.club.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final ClubMapper clubMapper;
    private final ActivityMapper activityMapper;
    private final ClubMemberMapper clubMemberMapper;
    private final ClubApplyMapper clubApplyMapper;
    private final NoticeMapper noticeMapper;
    private final UserMapper userMapper;

    public DashboardService(ClubMapper clubMapper, ActivityMapper activityMapper,
                            ClubMemberMapper clubMemberMapper, ClubApplyMapper clubApplyMapper,
                            NoticeMapper noticeMapper, UserMapper userMapper) {
        this.clubMapper = clubMapper;
        this.activityMapper = activityMapper;
        this.clubMemberMapper = clubMemberMapper;
        this.clubApplyMapper = clubApplyMapper;
        this.noticeMapper = noticeMapper;
        this.userMapper = userMapper;
    }

    public Map<String, Object> stats() {
        Map<String, Object> data = new HashMap<>();
        data.put("clubCount", clubMapper.selectAll().size());
        data.put("activityCount", activityMapper.selectAll().size());
        data.put("memberCount", clubMemberMapper.selectAll().size());
        data.put("applyCount", clubApplyMapper.selectAll().size());
        data.put("noticeCount", noticeMapper.selectAll().size());
        data.put("userCount", userMapper.findAll().size());
        return data;
    }
}
