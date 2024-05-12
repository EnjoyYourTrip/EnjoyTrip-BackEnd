package com.ssafy.enjoytrip.domain.member.controller;

import com.ssafy.enjoytrip.domain.member.model.Member;
import com.ssafy.enjoytrip.domain.member.service.MemberService;
import com.ssafy.enjoytrip.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody Member member) {
        try {
            Member findMember = memberService.login(member);
            if (findMember == null) {
                return ApiResponse.createFail("아이디와 비밀번호를 다시 확인해 주세요.");
            }
            return ApiResponse.createSuccess(findMember, "로그인 성공");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.createError("로그인 에러 발생");
        }

    }

    @PostMapping("/signup")
    public ApiResponse<?> sigUp(@RequestBody Member member) {
        try {
            memberService.insertMember(member);
            return ApiResponse.createSuccess(member.getMemberId(), "회원가입 성공");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.createError("회원가입 에러 발생");
        }
    }

    @GetMapping("/{memberId}")
    public ApiResponse<?> userInfo(@PathVariable Long memberId) {
        try {
            Member findMember = memberService.userInfo(memberId);
            if (findMember == null) {
                return ApiResponse.createFail("회원 상세 조회 실패");
            }
            return ApiResponse.createSuccess(findMember, "회원 상세 조회 성공");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.createError("회원 상세 조회 에러 발생");
        }
    }

    @GetMapping()
    public ApiResponse<?> listMember() {
        try {
            List<Member> findMembers = memberService.listMember();
            if (findMembers == null) {
                return ApiResponse.createFail("회원 목록 조회 실패");
            }
            return ApiResponse.createSuccess(findMembers, "회원 목록 조회 성공");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.createError("회원 목록 조회 에러 발생");
        }

    }

    @PatchMapping("/{memberId}")
    public ApiResponse<?> updateMember(@PathVariable Long memberId, @RequestBody Member member) {
        try {
            member.setMemberId(memberId);
            memberService.updateMember(member);
            return ApiResponse.createSuccess(memberId, "회원 정보 수정 성공");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.createError("회원 정보 수정 에러 발생");
        }
    }

    @DeleteMapping("/{memberId}")
    public ApiResponse<?> deleteMember(@PathVariable Long memberId) {
        try {
            memberService.deleteMember(memberId);
            return ApiResponse.createSuccess(memberId, "회원 삭제 성공");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.createError("회원 삭제 에러 발생");
        }
    }
}