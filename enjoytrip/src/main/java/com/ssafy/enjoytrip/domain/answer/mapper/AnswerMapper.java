package com.ssafy.enjoytrip.domain.answer.mapper;

import com.ssafy.enjoytrip.domain.answer.model.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface AnswerMapper {

    Answer answerInfo(Long answerId); // 답변 조회

    void insertAnswer(Answer answer); // 답변 등록

    void deleteAnswer(@Param("answerId") Long answerId, @Param("memberId") Long memberId); // 답변 삭제

    void updateAnswer(Answer answer); // 답변 수정

    boolean isAdminRole(Long memberId); // admin 확인
}
