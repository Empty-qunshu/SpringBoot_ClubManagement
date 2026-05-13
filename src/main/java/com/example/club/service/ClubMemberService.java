package com.example.club.service;

import com.example.club.entity.Club;
import com.example.club.entity.ClubMember;
import com.example.club.mapper.ClubMapper;
import com.example.club.mapper.ClubMemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClubMemberService {

    private final ClubMemberMapper memberMapper;
    private final ClubMapper clubMapper;

    public ClubMemberService(ClubMemberMapper memberMapper, ClubMapper clubMapper) {
        this.memberMapper = memberMapper;
        this.clubMapper = clubMapper;
    }

    public List<ClubMember> findAll() {
        return memberMapper.selectAll();
    }

    @Transactional
    public ClubMember join(ClubMember member) {
        member.setJoinTime(LocalDateTime.now());
        member.setMemberRole(1);
        member.setStatus(1);
        memberMapper.insert(member);
        clubMapper.increaseMemberCount(member.getClubId());
        return member;
    }

    @Transactional
    public void leave(Integer id) {
        ClubMember member = memberMapper.selectById(id);
        if (member == null || Integer.valueOf(0).equals(member.getStatus())) {
            return;
        }
        member.setStatus(0);
        memberMapper.update(member);
        clubMapper.decreaseMemberCount(member.getClubId());
    }

    public void updateRole(Integer id, Integer memberRole) {
        memberMapper.updateRole(id, memberRole);
    }

    public List<ClubMember> findByClubId(Integer clubId) {
        return memberMapper.selectByClubId(clubId);
    }

    public List<ClubMember> findByUserId(Integer userId) {
        return memberMapper.selectByUserId(userId);
    }

    public List<Map<String, Object>> findMyClubs(Integer userId) {
        List<ClubMember> members = memberMapper.selectByUserIdAndStatus(userId, 1);
        List<Map<String, Object>> result = new ArrayList<>();
        for (ClubMember member : members) {
            Club club = clubMapper.selectById(member.getClubId());
            if (club != null) {
                Map<String, Object> item = new HashMap<>();
                item.put("member", member);
                item.put("club", club);
                result.add(item);
            }
        }
        return result;
    }
}
