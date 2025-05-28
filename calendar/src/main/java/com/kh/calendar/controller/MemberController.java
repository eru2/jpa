package com.kh.calendar.controller;

import com.kh.calendar.dto.MemberDto;
import com.kh.calendar.entity.Member;
import com.kh.calendar.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping("/Enroll")
    public ResponseEntity<String> addMember (@RequestBody MemberDto.Create createDto){
        String user = memberService.createMember(createDto);
        return ResponseEntity.ok(user);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<MemberDto.Response> logInUser(@RequestBody MemberDto.Login loginDto) {
        Member member = memberService.logInUser(loginDto);
        return ResponseEntity.ok(MemberDto.Response.toDto(member));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MemberDto.Response> getMember(
            @PathVariable int userId) {
        return ResponseEntity.ok(memberService.getMember(userId));
    }
}
