package com.ssafy.enjoytrip.domain.question.mapper;

import com.ssafy.enjoytrip.domain.page.model.PageRequestParam;
import com.ssafy.enjoytrip.domain.question.model.dto.QuestionInfo;
import com.ssafy.enjoytrip.domain.question.model.dto.QuestionSearchCond;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.domain.question.model.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<QuestionInfo> listQuestion(PageRequestParam<Void> pageRequestParam);

    Question questionInfo(Long questionId);

    void insertQuestion(Question question);

    void deleteQuestion(Long questionId);

    void updateQuestion(Question question);

    void updateHit(Long questionId);

    List<Question> searchQuestion(QuestionSearchCond questionSearchCond);

    void updateToggleQuestionResponseStatus(Long questionId);

    boolean hasAdminResponse(Long questionId);

    int countQuestions(); // 전체 질문 개수 조회

}
