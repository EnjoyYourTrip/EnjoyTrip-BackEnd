package com.ssafy.enjoytrip.domain.question.model.dto;

import com.ssafy.enjoytrip.domain.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class QuestionInfo extends BaseTimeEntity {
    private Long questionId;
    private String title;
    private Integer hit;
    private Boolean hasResponse;
    private String nickname;
}
