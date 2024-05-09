package com.ssafy.enjoytrip.domain.member.service;

import com.ssafy.enjoytrip.domain.member.mapper.MemberMapper;
import com.ssafy.enjoytrip.domain.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Override
    public void joinMember(Member member) {
    }

    @Override
    public Member login(Member member) {
        return null;
    }

    @Override
    public void logout(Long memberId) {

    }

    @Override
    public Member userInfo(String memberId) {
        return null;
    }

    @Override
    public List<Member> listMember() {
        return null;
    }

    @Override
    public void modifyMember(Member member) {
    }

    @Override
    public void deleteMember(Member member) {
    }

    @Override
    public int idCheck(String memberId) {
        return 0;
    }

    @Override
    public int findPassword(Member member) {
        return 0;
    }
}
