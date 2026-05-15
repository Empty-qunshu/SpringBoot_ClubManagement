package com.example.club.service;

import com.example.club.entity.Notice;
import com.example.club.mapper.NoticeMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public List<Notice> findAll() {
        return noticeMapper.selectAll();
    }

    public List<Notice> search(String keyword) {
        List<Notice> notices = noticeMapper.selectAll();
        if (!StringUtils.hasText(keyword)) return notices;
        String kw = keyword.toLowerCase(Locale.ROOT);
        return notices.stream()
                .filter(notice -> contains(notice.getTitle(), kw)
                        || contains(notice.getContent(), kw)
                        || contains(notice.getPublisherId(), kw))
                .toList();
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

    private boolean contains(Object value, String keyword) {
        return value != null && String.valueOf(value).toLowerCase(Locale.ROOT).contains(keyword);
    }
}
