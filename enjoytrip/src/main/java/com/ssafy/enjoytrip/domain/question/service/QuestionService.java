package com.ssafy.enjoytrip.domain.question.service;

import com.ssafy.enjoytrip.domain.question.model.Question;
import com.ssafy.enjoytrip.domain.question.model.dto.SearchCondition;

import java.util.List;

public interface QuestionService {
    List<Question> listQuestion();

    Question questionInfo(Long questionId);

    void insertQuestion(Question question);

    void deleteQuestion(Long questionId);

    void updateQuestion(Question question);

    List<Question> searchQuestion(SearchCondition searchCondition);
}
