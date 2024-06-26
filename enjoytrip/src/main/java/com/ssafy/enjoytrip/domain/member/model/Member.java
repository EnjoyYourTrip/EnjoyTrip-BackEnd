package com.ssafy.enjoytrip.domain.member.model;

import com.ssafy.enjoytrip.domain.common.BaseTimeEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    private Long memberId;
    private String username;
    private String id;
    private String password;
    private String nickname;
    private String email;
    private Role role = Role.USER;
    private String refreshToken;
    private String passwordToken;
    private LocalDateTime passwordTokenExpiryDate;

    private Member(String username, String id, String password, String nickname, String email) {
        this.username = username;
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public static Member createMember(String username, String id, String password, String nickname, String email) {
        return new Member(username, id, password, nickname, email);
    }
}
