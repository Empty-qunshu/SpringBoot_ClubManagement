package com.example.club.service;

import com.example.club.entity.Club;
import com.example.club.mapper.ClubMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClubService {

    private final ClubMapper clubMapper;

    public ClubService(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }

    public List<Club> findAll() {
        return clubMapper.selectAll();
    }

    public Club findById(Integer id) {
        return clubMapper.selectById(id);
    }

    public Club add(Club club) {
        club.setCreateTime(LocalDateTime.now());
        club.setMemberCount(0);
        club.setStatus(1);
        clubMapper.insert(club);
        return club;
    }

    public void update(Club club) {
        clubMapper.update(club);
    }

    public void updateStatus(Integer id, Integer status) {
        clubMapper.updateStatus(id, status);
    }

    public void deleteById(Integer id) {
        clubMapper.deleteById(id);
    }

    public List<Club> findByLeaderId(Integer leaderId) {
        return clubMapper.selectByLeaderId(leaderId);
    }

    public List<Club> findByClubType(String clubType) {
        return clubMapper.selectByClubType(clubType);
    }

    public Map<String, Object> stats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", clubMapper.selectAll().size());
        return stats;
    }
}
