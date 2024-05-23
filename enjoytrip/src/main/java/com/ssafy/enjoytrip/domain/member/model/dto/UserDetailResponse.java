package com.ssafy.enjoytrip.domain.member.model.dto;

import com.ssafy.enjoytrip.domain.member.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponse {
    private Long memberId;
    private String username;
    private String id;
    private String password;
    private String nickname;
    private String email;
    private Role role;
    private String refreshToken;
}
