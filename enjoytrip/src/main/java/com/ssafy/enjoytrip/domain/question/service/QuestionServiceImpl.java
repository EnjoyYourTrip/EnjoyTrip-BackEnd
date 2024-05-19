package com.ssafy.enjoytrip.domain.question.service;

import com.ssafy.enjoytrip.domain.page.model.PageRequestParam;
import com.ssafy.enjoytrip.domain.question.mapper.QuestionMapper;
import com.ssafy.enjoytrip.domain.question.model.Question;
import com.ssafy.enjoytrip.domain.question.model.dto.QuestionInfo;
import com.ssafy.enjoytrip.domain.question.model.dto.QuestionSearchCond;
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
    public List<QuestionInfo> listQuestion(PageRequestParam<Void> pageRequestParam) {
        return questionMapper.listQuestion(pageRequestParam);
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
    public List<Question> searchQuestion(QuestionSearchCond questionSearchCond) {
        return questionMapper.searchQuestion(questionSearchCond);
    }

    @Override
    public int countQuestions() {
        return questionMapper.countQuestions(); // 전체 질문 개수 조회
    }
}
