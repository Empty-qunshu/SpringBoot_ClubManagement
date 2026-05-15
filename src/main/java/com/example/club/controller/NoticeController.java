package com.example.club.controller;

import com.example.club.entity.Notice;
import com.example.club.entity.PageResult;
import com.example.club.entity.Result;
import com.example.club.service.NoticeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/list")
    public Result list(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) Integer page,
                       @RequestParam(required = false) Integer pageSize) {
        var notices = noticeService.search(keyword);
        if (page != null || pageSize != null) {
            return Result.success(PageResult.of(notices, page, pageSize));
        }
        return Result.success(notices);
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id) {
        Notice notice = noticeService.findById(id);
        return notice != null ? Result.success(notice) : Result.error("公告不存在");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Notice notice) {
        return Result.success(noticeService.add(notice));
    }

    @PutMapping("/update")
    public Result update(@RequestBody Notice notice) {
        noticeService.update(notice);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        noticeService.deleteById(id);
        return Result.success();
    }
}
