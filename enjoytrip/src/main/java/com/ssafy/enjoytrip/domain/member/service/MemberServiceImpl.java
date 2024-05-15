package com.ssafy.enjoytrip.domain.member.service;

import com.ssafy.enjoytrip.domain.member.mapper.MemberMapper;
import com.ssafy.enjoytrip.domain.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public void insertMember(Member member) {
        memberMapper.insertMember(member);
        insertRole(member);
    }

    private void insertRole(Member member) {
        Long memberId = member.getMemberId();
        memberMapper.insertRole(memberId);
    }

    @Override
    @Transactional
    public Member login(Member member) {
        return memberMapper.login(member);
    }

    @Override
    public void logout(Long memberId) {
        // 추후 jwt로 로그아웃
    }

    @Override
    public Member userInfo(Long memberId) {
        return memberMapper.userInfo(memberId);
    }

    @Override
    public List<Member> listMember() {
        return memberMapper.listMember();
    }

    @Override
    @Transactional
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    @Override
    @Transactional
    public void deleteMember(Long memberId) {
        memberMapper.deleteRole(memberId);
        memberMapper.deleteMember(memberId);
    }

    @Override
    public int idCheck(String id) {
        return 0;
    }

    @Override
    public int findPassword(Member member) {
        return 0;
    }
}
