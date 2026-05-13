package com.example.club.controller;

import com.example.club.entity.ClubApply;
import com.example.club.entity.Result;
import com.example.club.service.ClubApplyService;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/apply")
public class ClubApplyController {

    private final ClubApplyService clubApplyService;

    public ClubApplyController(ClubApplyService clubApplyService) {
        this.clubApplyService = clubApplyService;
    }

    @GetMapping("/list")
    public Result list() {
        return Result.success(clubApplyService.findAll());
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id) {
        ClubApply apply = clubApplyService.findById(id);
        return apply != null ? Result.success(apply) : Result.error("申请不存在");
    }

    @PostMapping("/submit")
    public Result submit(@RequestBody ClubApply apply) {
        return Result.success(clubApplyService.submit(apply));
    }

    @PutMapping("/review/{id}")
    public Result review(@PathVariable Integer id, @RequestParam Integer status) {
        clubApplyService.review(id, status);
        return Result.success();
    }

    @GetMapping("/byUser/{userId}")
    public Result getByUserId(@PathVariable Integer userId) {
        return Result.success(clubApplyService.findByUserId(userId));
    }

    @GetMapping("/byClub/{clubId}")
    public Result getByClubId(@PathVariable Integer clubId) {
        return Result.success(clubApplyService.findByClubId(clubId));
    }

    @GetMapping("/pending")
    public Result getPending(@RequestParam Integer clubId) {
        return Result.success(clubApplyService.findPendingByClubId(clubId));
    }
}
