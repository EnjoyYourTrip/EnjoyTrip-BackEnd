package com.ssafy.enjoytrip.domain.member.model;

import lombok.Getter;

@Getter
public enum Role {
    USER, ADMIN;

    public String getName() {
        return name();
    }
}
