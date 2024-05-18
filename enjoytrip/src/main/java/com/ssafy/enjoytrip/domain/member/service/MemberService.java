package com.ssafy.enjoytrip.domain.member.service;


import com.ssafy.enjoytrip.domain.member.model.Member;
import com.ssafy.enjoytrip.domain.member.model.dto.LoginRequest;

import java.util.List;

public interface MemberService {
    void insertMember(Member member); // 회원가입

    Member login(LoginRequest loginRequest); // 로그인

    void logout(Long memberId); // 로그아웃

    Member userInfo(Long memberId); // 회원 상세 정보 조회

    void updateMember(Member member); // 회원 정보 수정

    int idCheck(String id); // 아이디 중복검사

    int findPassword(Member member);// 비밀번호 찾기

    /**
     * ADMIN
     */
    List<Member> listMember(); // 회원 목록 조회

    void deleteMember(Long memberId); // 회원 정보 삭제

}
