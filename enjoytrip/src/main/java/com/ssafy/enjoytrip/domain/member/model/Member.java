package com.ssafy.enjoytrip.domain.member.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long memberId;
    private String name;
    private String id;
    private String password;
    private String nickname;
    private String profileImg;
    private String email;
    private Role role; // ADMIN, USER
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
