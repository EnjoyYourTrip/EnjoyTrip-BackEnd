package com.ssafy.enjoytrip.domain.question.mapper;

import com.ssafy.enjoytrip.domain.question.model.dto.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.domain.question.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<Question> listQuestion();

    Question questionInfo(Long questionId);

    void insertQuestion(Question question);

    void deleteQuestion(Long questionId);

    void updateQuestion(Question question);

    void updateHit(Long questionId);

    List<Question> searchQuestion(SearchCondition searchCondition);

    void toggleQuestionResponseStatus(Long questionId);

    boolean hasAdminResponse(Long questionId);

}
