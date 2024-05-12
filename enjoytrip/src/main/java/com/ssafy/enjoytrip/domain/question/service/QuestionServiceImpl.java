package com.ssafy.enjoytrip.domain.question.service;

import com.ssafy.enjoytrip.domain.question.mapper.QuestionMapper;
import com.ssafy.enjoytrip.domain.question.model.Question;
import com.ssafy.enjoytrip.domain.question.model.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;

    @Override
    public List<Question> listQuestion() {
        return questionMapper.listQuestion();
    }

    @Override
    public Question questionInfo(Long questionId) {
        questionMapper.updateHit(questionId); // 조회수 증가
        return questionMapper.questionInfo(questionId);
    }

    @Override
    public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionMapper.deleteQuestion(questionId);
    }

    @Override
    public void updateQuestion(Question question) {
        questionMapper.updateQuestion(question);
    }

    @Override
    public List<Question> searchQuestion(SearchCondition searchCondition) {
        return questionMapper.searchQuestion(searchCondition);
    }
}
