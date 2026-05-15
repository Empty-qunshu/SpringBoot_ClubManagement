package com.example.club.controller;

import com.example.club.entity.ClubMember;
import com.example.club.entity.PageResult;
import com.example.club.entity.Result;
import com.example.club.service.ClubMemberService;
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
@RequestMapping("/api/member")
public class ClubMemberController {

    private final ClubMemberService memberService;

    public ClubMemberController(ClubMemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/list")
    public Result list(@RequestParam(required = false) Integer status,
                       @RequestParam(required = false) String keyword,
                       @RequestParam(required = false) Integer page,
                       @RequestParam(required = false) Integer pageSize) {
        var members = memberService.search(status, keyword);
        if (page != null || pageSize != null) {
            return Result.success(PageResult.of(members, page, pageSize));
        }
        return Result.success(members);
    }

    @PostMapping("/join")
    public Result join(@RequestBody ClubMember member) {
        return Result.success(memberService.join(member));
    }

    @PutMapping("/leave/{id}")
    public Result leave(@PathVariable Integer id) {
        memberService.leave(id);
        return Result.success();
    }

    @PutMapping("/updateRole/{id}")
    public Result updateRole(@PathVariable Integer id, @RequestParam Integer memberRole) {
        memberService.updateRole(id, memberRole);
        return Result.success();
    }

    @GetMapping("/byClub/{clubId}")
    public Result getByClubId(@PathVariable Integer clubId) {
        return Result.success(memberService.findByClubId(clubId));
    }

    @GetMapping("/byUser/{userId}")
    public Result getByUserId(@PathVariable Integer userId) {
        return Result.success(memberService.findByUserId(userId));
    }

    @GetMapping("/myClubs/{userId}")
    public Result getMyClubs(@PathVariable Integer userId) {
        return Result.success(memberService.findMyClubs(userId));
    }
}
