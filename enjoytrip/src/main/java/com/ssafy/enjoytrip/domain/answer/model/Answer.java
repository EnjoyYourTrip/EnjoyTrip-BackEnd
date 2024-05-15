package com.ssafy.enjoytrip.domain.answer.model;

import com.ssafy.enjoytrip.domain.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Answer extends BaseTimeEntity {
    private Long answerId;
    private Long questionId;
    private Long memberId;
    private String content;

    public Answer(Long questionId, Long memberId, String content) {
        this.questionId = questionId;
        this.memberId = memberId;
        this.content = content;
    }

    public static Answer createAnswer(Long questionId, Long memberId, String content) {
        return new Answer(questionId, memberId, content);
    }
}