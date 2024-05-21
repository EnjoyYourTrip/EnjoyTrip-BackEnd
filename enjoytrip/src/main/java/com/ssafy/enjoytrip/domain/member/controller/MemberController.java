package com.ssafy.enjoytrip.domain.member.controller;

import com.ssafy.enjoytrip.domain.member.model.Member;
import com.ssafy.enjoytrip.domain.member.model.dto.ForgotPasswordRequest;
import com.ssafy.enjoytrip.domain.member.model.dto.LoginRequest;
import com.ssafy.enjoytrip.domain.member.model.dto.LoginResponse;
import com.ssafy.enjoytrip.domain.member.model.dto.ResetPasswordRequest;
import com.ssafy.enjoytrip.domain.member.service.MemberService;
import com.ssafy.enjoytrip.util.ApiResponse;
import com.ssafy.enjoytrip.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Member findMember = memberService.login(loginRequest);
            if (findMember == null) {
                return ApiResponse.createFail("아이디 또는 비밀번호를 다시 확인해 주세요.");
            }

            String accessToken = jwtUtil.createAccessToken(findMember.getMemberId());
            String refreshToken = jwtUtil.createRefreshToken(findMember.getMemberId());
            log.debug("access token : {}", accessToken);
            log.debug("refresh token : {}", refreshToken);

            // 발급받은 refresh token 을 DB에 저장.
            memberService.saveRefreshToken(findMember.getMemberId(), refreshToken);

            return ApiResponse.createSuccess(new LoginResponse(accessToken, refreshToken), "로그인 성공");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.createError("로그인 에러 발생");
        }

    }

    @GetMapping("/logout/{memberId}")
    public ApiResponse<?> logout(@PathVariable Long memberId) {
        try {
            memberService.deleteRefreshToken(memberId);
            return ApiResponse.createSuccessWithNoContent();
        } catch (Exception e) {
            log.error("로그아웃 실패 : {}", e);
            return ApiResponse.createError("로그아웃 에러 발생");
        }
    }

    @PostMapping("/signup")
    public ApiResponse<?> signUp(@Valid @RequestBody Member member) {
        try {
            memberService.insertMember(member);
            return ApiResponse.createSuccess(member.getMemberId(), "회원가입 성공");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.createError("회원가입 에러 발생");
        }
    }

    @GetMapping("/{memberId}")
    public ApiResponse<?> userInfo(@PathVariable Long memberId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (jwtUtil.checkToken(token)) {
            log.info("사용 가능한 토큰!!!");
            try {
                Long tokenMemberId = jwtUtil.getMemberId(token);
                if (!memberId.equals(tokenMemberId)) {
                    log.error("토큰의 사용자 ID와 요청된 사용자 ID 불일치!!!");
                    return ApiResponse.createFail("접근이 거부되었습니다.");
                }

                Member findMember = memberService.userInfo(memberId);
                if (findMember == null) {
                    log.error("회원 존재X !!!");
                    return ApiResponse.createFail("회원 상세 조회 실패");
                }
                return ApiResponse.createSuccess(findMember, "회원 상세 조회 성공");
            } catch (Exception e) {
                log.error(e.getMessage());
                return ApiResponse.createError("회원 상세 조회 에러 발생");
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            return ApiResponse.createFail("회원 상세 조회 실패");
        }
    }

    @GetMapping
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

    @PostMapping("/forgot-password")
    public ApiResponse<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        try {
            memberService.processForgotPassword(request.getUsername(), request.getEmail());
            return ApiResponse.createSuccess(null, "비밀번호 재설정 이메일을 발송했습니다.");
        } catch (Exception e) {
            log.error("비밀번호 재설정 요청 실패: {}", e.getMessage());
            return ApiResponse.createError("비밀번호 재설정 요청 실패");
        }
    }

    @PostMapping("/reset-password")
    public ApiResponse<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        try {
            memberService.resetPassword(request.getPasswordToken(), request.getNewPassword());
            return ApiResponse.createSuccess(null, "비밀번호가 성공적으로 재설정되었습니다.");
        } catch (Exception e) {
            log.error("비밀번호 재설정 실패: {}", e.getMessage());
            return ApiResponse.createError("비밀번호 재설정 실패");
        }
    }

    @PatchMapping("/{memberId}")
    public ApiResponse<?> updateMember(@PathVariable Long memberId, @Valid @RequestBody Member member) {
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

    @PostMapping("/refresh/{memberId}")
    public ApiResponse<?> refreshToken(@PathVariable Long memberId, HttpServletRequest request) {
        String token = request.getHeader("refreshToken");
        log.debug("token : {}, memberId : {}", token, memberId);
        if (jwtUtil.checkToken(token)) {
            if (token.equals(memberService.getRefreshToken(memberId))) {
                String accessToken = jwtUtil.createAccessToken(memberId);
                log.debug("token : {}", accessToken);
                log.debug("정상적으로 access token 재발급!!!");
                return ApiResponse.createSuccess(accessToken, "access token 생성");
            } else {
                return ApiResponse.createFail("회원 정보와 refresh token 일치 하지 않음!");
            }
        } else {
            log.debug("refresh token 도 사용 불가!!!!!!!");
            return ApiResponse.createFail("refresh token 만료");
        }
    }

}