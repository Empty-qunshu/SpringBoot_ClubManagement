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
    private int totalPages;
    private boolean hasPrevious;
    private boolean hasNext;

    public static <T> PageResult<T> of(List<T> source, Integer page, Integer pageSize) {
        List<T> data = source == null ? Collections.emptyList() : source;
        int safePage = page == null || page < 1 ? 1 : page;
        int safeSize = pageSize == null || pageSize < 1 ? 10 : Math.min(pageSize, 100);
        int totalPages = (int) Math.ceil(data.size() * 1.0 / safeSize);
        int from = Math.min((safePage - 1) * safeSize, data.size());
        int to = Math.min(from + safeSize, data.size());
        return new PageResult<>(
                data.subList(from, to),
                data.size(),
                safePage,
                safeSize,
                totalPages,
                safePage > 1,
                safePage < Math.max(1, totalPages)
        );
    }
}
