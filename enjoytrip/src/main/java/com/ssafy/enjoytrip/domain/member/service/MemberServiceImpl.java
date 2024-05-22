package com.ssafy.enjoytrip.domain.member.service;

import com.ssafy.enjoytrip.domain.member.mapper.MemberMapper;
import com.ssafy.enjoytrip.domain.member.model.Member;
import com.ssafy.enjoytrip.domain.member.model.dto.LoginRequest;
import com.ssafy.enjoytrip.exception.EmailNotFoundException;
import com.ssafy.enjoytrip.exception.InvalidTokenException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Value("${password.reset.url}")
    private String passwordResetLink;

    @Override
    public void insertMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberMapper.insertMember(member);
        memberMapper.insertRole(member.getMemberId());
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

        sendResetPasswordEmail(member.getUsername(), member.getEmail(), passwordToken, passwordTokenExpiryDate);
    }

    // HTML 이메일 발송 메서드
    private void sendResetPasswordEmail(String username, String email, String token, LocalDateTime expiryDate) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedExpiryDate = expiryDate.format(formatter);

            StringBuilder htmlMsg = new StringBuilder();
            htmlMsg.append("<!DOCTYPE html>")
                    .append("<html>")
                    .append("<head>")
                    .append("<style>")
                    .append("    body { font-family: 'Helvetica Neue', Arial, sans-serif; background-color: #f4f4f7; margin: 0; padding: 0; font-size: 18px; }")
                    .append("    .container { max-width: 800px; margin: 40px auto; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); text-align: center; }")
                    .append("    h2 { color: #333333; margin-bottom: 20px; font-size: 24px; }")
                    .append("    p { line-height: 1.8; color: #4d4d4d; margin: 10px 0; font-size: 18px; }")
                    .append("    a { color: #1a73e8; text-decoration: none; font-weight: bold; font-size: 18px; }")
                    .append("    a:hover { text-decoration: underline; }")
                    .append("    .token { font-weight: bold; background-color: #f2f2f2; padding: 10px; border-radius: 5px; display: inline-block; margin: 10px 0; font-size: 18px; color: #333333; }")
                    .append("    .expiry { color: #888; font-size: 0.9em; margin-top: 10px; font-size: 18px; }")
                    .append("    .footer { margin-top: 30px; font-size: 0.9em; color: #888; text-align: center; font-size: 18px; }")
                    .append("    .logo { text-align: center; margin-bottom: 40px; }")
                    .append("    .logo img { max-width: 150px; }")
                    .append("</style>")
                    .append("</head>")
                    .append("<body>")
                    .append("<div class='container'>")
                    .append("<div class='logo'>")
                    .append("<img src='https://www.example.com/logo.png' alt='Trip-Helper Logo'>")
                    .append("</div>")
                    .append("<h2>비밀번호 재설정 요청</h2>")
                    .append("<p>안녕하세요 ").append(username).append("님, Trip-Helper입니다.</p>")
                    .append("<p>비밀번호를 재설정하려면 아래 링크를 클릭하고, 제공된 토큰을 입력하세요:</p>")
                    .append("<p><a href=\"").append(passwordResetLink).append("\">비밀번호 재설정 링크</a></p>")
                    .append("<p>토큰: <span class='token'>").append(token).append("</span></p>")
                    .append("<p class='expiry'>토큰 만료 시간: ").append(formattedExpiryDate).append("</p>")
                    .append("<p>이 요청을 하지 않았다면, 이 이메일을 무시하세요.</p>")
                    .append("</div>")
                    .append("<div class='footer'>")
                    .append("<p>© 2024 Trip-Helper. All rights reserved.</p>")
                    .append("</div>")
                    .append("</body>")
                    .append("</html>");

            helper.setTo(email);
            helper.setSubject("비밀번호 재설정");
            helper.setText(htmlMsg.toString(), true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailSendException("비밀번호 재설정 이메일 전송 실패: " + e.getMessage());
        }
    }

    @Override
    public void resetPassword(String passwordToken, String newPassword) {
        Member findMember = memberMapper.findByPasswordToken(passwordToken);
        if (findMember == null || findMember.getPasswordTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("유효하지 않거나 만료된 토큰입니다.");
        }

        findMember.setPassword(passwordEncoder.encode(newPassword));
        findMember.setPasswordToken(null);
        findMember.setPasswordTokenExpiryDate(null);
        memberMapper.updateMember(findMember);
    }

}
