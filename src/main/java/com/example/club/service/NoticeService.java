package com.example.club.service;

import com.example.club.entity.Notice;
import com.example.club.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public List<Notice> findAll() {
        return noticeMapper.selectAll();
    }

    public Notice findById(Integer id) {
        return noticeMapper.selectById(id);
    }

    public Notice add(Notice notice) {
        notice.setCreateTime(LocalDateTime.now());
        noticeMapper.insert(notice);
        return notice;
    }

    public void update(Notice notice) {
        noticeMapper.update(notice);
    }

    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }
}
