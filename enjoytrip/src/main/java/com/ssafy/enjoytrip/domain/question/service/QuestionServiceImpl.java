package com.ssafy.enjoytrip.domain.question.service;

import com.ssafy.enjoytrip.domain.question.mapper.QuestionMapper;
import com.ssafy.enjoytrip.domain.question.model.Question;
import com.ssafy.enjoytrip.domain.question.model.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;

    @Override
    public List<Question> listQuestion() {
        return questionMapper.listQuestion();
    }

    @Override
    @Transactional
    public Question questionInfo(Long questionId) {
        questionMapper.updateHit(questionId); // 조회수 증가
        return questionMapper.questionInfo(questionId);
    }

    @Override
    @Transactional
    public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }

    @Override
    @Transactional
    public void deleteQuestion(Long questionId) {
        questionMapper.deleteQuestion(questionId);
    }

    @Override
    @Transactional
    public void updateQuestion(Question question) {
        questionMapper.updateQuestion(question);
    }

    @Override
    public List<Question> searchQuestion(SearchCondition searchCondition) {
        return questionMapper.searchQuestion(searchCondition);
    }
}
