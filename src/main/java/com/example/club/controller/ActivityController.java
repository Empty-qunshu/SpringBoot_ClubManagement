package com.example.club.controller;

import com.example.club.entity.Activity;
import com.example.club.entity.PageResult;
import com.example.club.entity.Result;
import com.example.club.service.ActivityService;
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
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/list")
    public Result list(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) Integer page,
                       @RequestParam(required = false) Integer pageSize) {
        var activities = activityService.search(keyword);
        if (page != null || pageSize != null) {
            return Result.success(PageResult.of(activities, page, pageSize));
        }
        return Result.success(activities);
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id) {
        Activity activity = activityService.findById(id);
        return activity != null ? Result.success(activity) : Result.error("活动不存在");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Activity activity) {
        return Result.success(activityService.add(activity));
    }

    @PutMapping("/update")
    public Result update(@RequestBody Activity activity) {
        activityService.update(activity);
        return Result.success();
    }

    @PutMapping("/status/{id}")
    public Result updateStatus(@PathVariable Integer id, @RequestParam Integer status) {
        activityService.updateStatus(id, status);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        activityService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/byClub/{clubId}")
    public Result getByClubId(@PathVariable Integer clubId) {
        return Result.success(activityService.findByClubId(clubId));
    }
}
