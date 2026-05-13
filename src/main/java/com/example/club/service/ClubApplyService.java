package com.example.club.service;

import com.example.club.entity.ClubApply;
import com.example.club.mapper.ClubApplyMapper;
import com.example.club.mapper.RecruitmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClubApplyService {

    private final ClubApplyMapper clubApplyMapper;
    private final RecruitmentMapper recruitmentMapper;

    public ClubApplyService(ClubApplyMapper clubApplyMapper, RecruitmentMapper recruitmentMapper) {
        this.clubApplyMapper = clubApplyMapper;
        this.recruitmentMapper = recruitmentMapper;
    }

    public List<ClubApply> findAll() {
        return clubApplyMapper.selectAll();
    }

    public ClubApply findById(Integer id) {
        return clubApplyMapper.selectById(id);
    }

    @Transactional
    public ClubApply submit(ClubApply apply) {
        apply.setApplyTime(LocalDateTime.now());
        apply.setStatus(0);
        clubApplyMapper.insert(apply);
        recruitmentMapper.increaseCurrentCount(apply.getRecruitmentId());
        return apply;
    }

    public void review(Integer id, Integer status) {
        ClubApply apply = clubApplyMapper.selectById(id);
        if (apply == null) {
            return;
        }
        apply.setStatus(status);
        apply.setReviewTime(LocalDateTime.now());
        clubApplyMapper.update(apply);
    }

    public List<ClubApply> findByUserId(Integer userId) {
        return clubApplyMapper.selectByUserId(userId);
    }

    public List<ClubApply> findByClubId(Integer clubId) {
        return clubApplyMapper.selectByClubId(clubId);
    }

    public List<ClubApply> findPendingByClubId(Integer clubId) {
        return clubApplyMapper.selectByClubIdAndStatus(clubId, 0);
    }
}
