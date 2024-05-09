package com.ssafy.enjoytrip.domain.member.service;


import com.ssafy.enjoytrip.domain.member.model.Member;

import java.util.List;

public interface MemberService {
    void joinMember(Member member); // 회원가입

    Member login(Member member); // 로그인

    void logout(Long memberId); // 로그아웃

    Member userInfo(String memberId); // 회원 상세 정보 조회

    List<Member> listMember(); // 회원 목록 조회

    void modifyMember(Member member); // 회원 정보 수정

    void deleteMember(Member member); // 회원 정보 삭제

    int idCheck(String memberId);// 아이디 중복검사

    int findPassword(Member member);// 비밀번호 찾기
}
