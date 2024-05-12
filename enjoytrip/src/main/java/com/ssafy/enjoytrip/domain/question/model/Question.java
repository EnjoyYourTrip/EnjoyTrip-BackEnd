package com.ssafy.enjoytrip.domain.question.model;

import com.ssafy.enjoytrip.domain.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseTimeEntity {
    private Long questionId;
    private String title;
    private String content;
    private Integer hit;
    private Boolean hasResponse;
    private Long memberId;
}
