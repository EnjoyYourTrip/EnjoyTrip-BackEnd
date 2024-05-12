package com.ssafy.enjoytrip.domain.member.model;

import com.ssafy.enjoytrip.domain.common.BaseTimeEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {
    private Long memberId;
    private String username;
    private String id;
    private String password;
    private String nickname;
    private String email;
    private Role role;
}
