package com.ssafy.enjoytrip.domain.answer.model;

import com.ssafy.enjoytrip.domain.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends BaseTimeEntity {
    private Long answerId;
    private Long questionId;
    private Long memberId;
    private String content;
}