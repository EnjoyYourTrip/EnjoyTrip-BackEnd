package com.ssafy.enjoytrip.domain.question.service;

import com.ssafy.enjoytrip.domain.page.model.PageRequestParam;
import com.ssafy.enjoytrip.domain.question.model.Question;
import com.ssafy.enjoytrip.domain.question.model.dto.QuestionInfo;
import com.ssafy.enjoytrip.domain.question.model.dto.QuestionSearchCond;

import java.util.List;

public interface QuestionService {
    List<QuestionInfo> listQuestion(PageRequestParam<Void> pageRequestParam);

    Question questionInfo(Long questionId);

    void insertQuestion(Question question);

    void deleteQuestion(Long questionId);

    void updateQuestion(Question question);

    List<Question> searchQuestion(QuestionSearchCond questionSearchCond);

    int countQuestions();

}
