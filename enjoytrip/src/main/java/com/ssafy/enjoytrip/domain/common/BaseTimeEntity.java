package com.ssafy.enjoytrip.domain.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class BaseTimeEntity {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
