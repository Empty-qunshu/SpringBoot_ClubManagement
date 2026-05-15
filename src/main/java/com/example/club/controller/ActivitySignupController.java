package com.example.club.controller;

import com.example.club.entity.ActivitySignup;
import com.example.club.entity.PageResult;
import com.example.club.entity.Result;
import com.example.club.service.ActivitySignupService;
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
@RequestMapping("/api/signup")
public class ActivitySignupController {

    private final ActivitySignupService signupService;

    public ActivitySignupController(ActivitySignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping("/list")
    public Result list(@RequestParam(required = false) Integer status,
                       @RequestParam(required = false) String keyword,
                       @RequestParam(required = false) Integer page,
                       @RequestParam(required = false) Integer pageSize) {
        var signups = signupService.search(status, keyword);
        if (page != null || pageSize != null) {
            return Result.success(PageResult.of(signups, page, pageSize));
        }
        return Result.success(signups);
    }

    @PostMapping("/join")
    public Result join(@RequestBody ActivitySignup signup) {
        return Result.success(signupService.join(signup));
    }

    @PutMapping("/cancel/{id}")
    public Result cancel(@PathVariable Integer id) {
        signupService.cancel(id);
        return Result.success();
    }

    @PutMapping("/checkin/{id}")
    public Result checkin(@PathVariable Integer id) {
        signupService.checkin(id);
        return Result.success();
    }

    @GetMapping("/byUser/{userId}")
    public Result getByUserId(@PathVariable Integer userId) {
        return Result.success(signupService.findByUserId(userId));
    }

    @GetMapping("/byActivity/{activityId}")
    public Result getByActivityId(@PathVariable Integer activityId) {
        return Result.success(signupService.findByActivityId(activityId));
    }
}
