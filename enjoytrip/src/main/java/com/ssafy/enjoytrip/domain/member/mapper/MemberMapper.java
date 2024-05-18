package com.ssafy.enjoytrip.domain.member.mapper;

import com.ssafy.enjoytrip.domain.member.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    void insertMember(Member member); // 회원가입

    Member login(String id); // 로그인

    Member userInfo(Long memberId); // 회원 상세 정보 조회

    List<Member> listMember(); // 회원 목록 조회

    void updateMember(Member member); // 회원 정보 삭제

    void deleteMember(Long memberId); // 회원 정보 수정

    int findPassword(Member member);// 비밀번호 찾기

    void insertRole(Long memberId); // 권한 등록

    void deleteRole(Long memberId); // 권한 삭제

    void saveRefreshToken(@Param("memberId") Long memberId, @Param("token") String token);

    String getRefreshToken(Long memberId);

    void deleteRefreshToken(@Param("memberId") Long memberId, @Param("token") String token);

}
