package com.ssafy.enjoytrip.domain.member.service;


import com.ssafy.enjoytrip.domain.member.model.Member;
import com.ssafy.enjoytrip.domain.member.model.dto.LoginRequest;
import com.ssafy.enjoytrip.domain.member.model.dto.UserDetailResponse;

import java.util.List;

public interface MemberService {
    void insertMember(Member member); // 회원가입

    Member login(LoginRequest loginRequest); // 로그인

    UserDetailResponse userInfo(Long memberId); // 회원 상세 정보 조회

    void updateMember(Member member); // 회원 정보 수정

    /**
     * ADMIN
     */
    List<Member> listMember(); // 회원 목록 조회

    void deleteMember(Long memberId); // 회원 정보 삭제

    /**
     * JWT
     */
    void saveRefreshToken(Long memberId, String refreshToken);

    String getRefreshToken(Long memberId);

    void deleteRefreshToken(Long memberId);


    /**
     * MAIL
     */
    void processForgotPassword(String username, String email);

    void resetPassword(String passwordToken, String newPassword);
}
