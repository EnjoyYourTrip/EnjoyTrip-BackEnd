package com.ssafy.enjoytrip.domain.answer.service;

import com.ssafy.enjoytrip.domain.answer.model.Answer;

public interface AnswerService {
    Answer answerInfo(Long questionId); // 답변 조회

    void insertAnswer(Answer answer); // 답변 등록

    void deleteAnswer(Answer answer); // 답변 삭제

    void updateAnswer(Answer answer); // 답변 수정
}
