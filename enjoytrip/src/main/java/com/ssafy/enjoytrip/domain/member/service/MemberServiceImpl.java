package com.ssafy.enjoytrip.domain.member.service;

import com.ssafy.enjoytrip.domain.member.mapper.MemberMapper;
import com.ssafy.enjoytrip.domain.member.model.Member;
import com.ssafy.enjoytrip.domain.member.model.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void insertMember(Member member) {
        passwordHashing(member);
        memberMapper.insertMember(member);
        insertRole(member);
    }

    private void passwordHashing(Member member) {
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
    }

    private void insertRole(Member member) {
        Long memberId = member.getMemberId();
        memberMapper.insertRole(memberId);
    }

    @Override
    public Member login(LoginRequest loginRequest) {
        Member storedMember = memberMapper.login(loginRequest.getId());
        if (storedMember != null && passwordEncoder.matches(loginRequest.getPassword(), storedMember.getPassword())) {
            return storedMember;
        } else {
            return null; // 비밀번호가 일치하지 않으면 null 반환
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Member userInfo(Long memberId) {
        return memberMapper.userInfo(memberId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> listMember() {
        return memberMapper.listMember();
    }

    @Override
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    @Override
    public void deleteMember(Long memberId) {
        memberMapper.deleteRole(memberId);
        memberMapper.deleteMember(memberId);
    }

    @Override
    public int findPassword(Member member) {
        return 0;
    }

    @Override
    public void saveRefreshToken(Long memberId, String refreshToken) {
        memberMapper.saveRefreshToken(memberId, refreshToken);
    }

    @Override
    public String getRefreshToken(Long memberId) {
        return memberMapper.getRefreshToken(memberId);
    }

    @Override
    public void deleteRefreshToken(Long memberId) {
        memberMapper.deleteRefreshToken(memberId, null);
    }
}
