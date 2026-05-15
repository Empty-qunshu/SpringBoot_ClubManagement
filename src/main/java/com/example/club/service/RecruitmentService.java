package com.example.club.service;

import com.example.club.entity.Recruitment;
import com.example.club.mapper.RecruitmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class RecruitmentService {

    private final RecruitmentMapper recruitmentMapper;

    public RecruitmentService(RecruitmentMapper recruitmentMapper) {
        this.recruitmentMapper = recruitmentMapper;
    }

    public List<Recruitment> findAll() {
        return recruitmentMapper.selectAll();
    }

    public List<Recruitment> search(String keyword) {
        List<Recruitment> recruitments = recruitmentMapper.selectAll();
        if (!StringUtils.hasText(keyword)) return recruitments;
        String kw = keyword.toLowerCase(Locale.ROOT);
        return recruitments.stream()
                .filter(recruitment -> contains(recruitment.getTitle(), kw)
                        || contains(recruitment.getContent(), kw)
                        || contains(recruitment.getRequirement(), kw)
                        || contains(recruitment.getClubId(), kw)
                        || contains(recruitment.getPublisherId(), kw))
                .toList();
    }

    public Recruitment findById(Integer id) {
        return recruitmentMapper.selectById(id);
    }

    public Recruitment add(Recruitment recruitment) {
        recruitment.setCreateTime(LocalDateTime.now());
        recruitment.setCurrentCount(0);
        recruitment.setStatus(1);
        recruitmentMapper.insert(recruitment);
        return recruitment;
    }

    public void update(Recruitment recruitment) {
        recruitmentMapper.update(recruitment);
    }

    public void updateStatus(Integer id, Integer status) {
        recruitmentMapper.updateStatus(id, status);
    }

    public void deleteById(Integer id) {
        recruitmentMapper.deleteById(id);
    }

    public List<Recruitment> findByClubId(Integer clubId) {
        return recruitmentMapper.selectByClubId(clubId);
    }

    private boolean contains(Object value, String keyword) {
        return value != null && String.valueOf(value).toLowerCase(Locale.ROOT).contains(keyword);
    }
}
