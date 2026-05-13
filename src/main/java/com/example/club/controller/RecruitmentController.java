package com.example.club.controller;

import com.example.club.entity.Recruitment;
import com.example.club.entity.Result;
import com.example.club.service.RecruitmentService;
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
@RequestMapping("/api/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    public RecruitmentController(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @GetMapping("/list")
    public Result list() {
        return Result.success(recruitmentService.findAll());
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id) {
        Recruitment recruitment = recruitmentService.findById(id);
        return recruitment != null ? Result.success(recruitment) : Result.error("招新信息不存在");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Recruitment recruitment) {
        return Result.success(recruitmentService.add(recruitment));
    }

    @PutMapping("/update")
    public Result update(@RequestBody Recruitment recruitment) {
        recruitmentService.update(recruitment);
        return Result.success();
    }

    @PutMapping("/status/{id}")
    public Result updateStatus(@PathVariable Integer id, @RequestParam Integer status) {
        recruitmentService.updateStatus(id, status);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        recruitmentService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/byClub/{clubId}")
    public Result getByClubId(@PathVariable Integer clubId) {
        return Result.success(recruitmentService.findByClubId(clubId));
    }
}
