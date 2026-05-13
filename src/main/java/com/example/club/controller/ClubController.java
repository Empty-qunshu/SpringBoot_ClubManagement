package com.example.club.controller;

import com.example.club.entity.Club;
import com.example.club.entity.Result;
import com.example.club.service.ClubService;
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
@RequestMapping("/api/club")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/list")
    public Result list() {
        return Result.success(clubService.findAll());
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id) {
        Club club = clubService.findById(id);
        return club != null ? Result.success(club) : Result.error("社团不存在");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Club club) {
        return Result.success(clubService.add(club));
    }

    @PutMapping("/update")
    public Result update(@RequestBody Club club) {
        clubService.update(club);
        return Result.success();
    }

    @PutMapping("/status/{id}")
    public Result updateStatus(@PathVariable Integer id, @RequestParam Integer status) {
        clubService.updateStatus(id, status);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        clubService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/byLeader/{leaderId}")
    public Result getByLeader(@PathVariable Integer leaderId) {
        return Result.success(clubService.findByLeaderId(leaderId));
    }

    @GetMapping("/byType")
    public Result getByType(@RequestParam String clubType) {
        return Result.success(clubService.findByClubType(clubType));
    }

    @GetMapping("/stats")
    public Result stats() {
        return Result.success(clubService.stats());
    }
}
