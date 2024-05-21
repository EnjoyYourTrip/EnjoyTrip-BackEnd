package com.ssafy.enjoytrip.domain.member.service;

import com.ssafy.enjoytrip.domain.member.mapper.MemberMapper;
import com.ssafy.enjoytrip.domain.member.model.Member;
import com.ssafy.enjoytrip.domain.member.model.dto.LoginRequest;
import com.ssafy.enjoytrip.exception.EmailNotFoundException;
import com.ssafy.enjoytrip.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Value("${app.frontend.url}")
    private String frontendUrl;


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

    @Override
    public void processForgotPassword(String username, String email) {

        Member member = memberMapper.findByUsernameAndEmail(username, email);
        if (member == null) {
            throw new EmailNotFoundException("등록되지 않은 이메일 또는 사용자 이름입니다.");
        }

        String passwordToken = UUID.randomUUID().toString();
        LocalDateTime passwordTokenExpiryDate = LocalDateTime.now().plusMinutes(10);

        member.setPasswordToken(passwordToken);
        member.setPasswordTokenExpiryDate(passwordTokenExpiryDate);
        memberMapper.savePasswordResetToken(member.getMemberId(), passwordToken, passwordTokenExpiryDate);

        String resetLink = frontendUrl + "/reset-password?token=" + passwordToken;
        sendResetPasswordEmail(member.getEmail(), resetLink);
    }

    private void sendResetPasswordEmail(String email, String resetLink) {
        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("비밀번호 재설정");
            message.setText("비밀번호를 재설정하려면 다음 링크를 클릭하세요: " + resetLink);
            mailSender.send(message);
        } catch (MailException e) {
            throw new MailSendException("비밀번호 재설정 이메일 전송 실패: " + e.getMessage());
        }
    }

    @Override
    public void resetPassword(String passwordToken, String newPassword) {
        Member member = memberMapper.findByPasswordToken(passwordToken);
        if (member == null || member.getPasswordTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("유효하지 않거나 만료된 토큰입니다.");
        }

        member.setPassword(passwordEncoder.encode(newPassword));
        member.setPasswordToken(null);
        member.setPasswordTokenExpiryDate(null);
        memberMapper.updateMember(member);
    }

}
