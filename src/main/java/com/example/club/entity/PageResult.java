package com.example.club.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private List<T> records;
    private long total;
    private int page;
    private int pageSize;

    public static <T> PageResult<T> of(List<T> source, Integer page, Integer pageSize) {
        List<T> data = source == null ? Collections.emptyList() : source;
        int safePage = page == null || page < 1 ? 1 : page;
        int safeSize = pageSize == null || pageSize < 1 ? 10 : pageSize;
        int from = Math.min((safePage - 1) * safeSize, data.size());
        int to = Math.min(from + safeSize, data.size());
        return new PageResult<>(data.subList(from, to), data.size(), safePage, safeSize);
    }
}
