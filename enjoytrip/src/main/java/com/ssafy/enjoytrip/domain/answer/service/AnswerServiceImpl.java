package com.ssafy.enjoytrip.domain.answer.service;

import com.ssafy.enjoytrip.domain.answer.mapper.AnswerMapper;
import com.ssafy.enjoytrip.domain.answer.model.Answer;
import com.ssafy.enjoytrip.domain.question.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AnswerServiceImpl implements AnswerService {

    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    @Override
    public Answer answerInfo(Long questionId) {
        return answerMapper.answerInfo(questionId);
    }

    @Override
    @Transactional
    public void insertAnswer(Answer answer) {
        Long memberId = answer.getMemberId();
        Long questionId = answer.getQuestionId();
        if (answerMapper.isAdminRole(memberId) && questionMapper.hasAdminResponse(questionId)) {
            answerMapper.insertAnswer(answer);
            questionMapper.updateToggleQuestionResponseStatus(answer.getQuestionId());
        }
        else { // insert, delete, update 진행시 모두 필요함
//            throw new UnauthorizedException("You are not authorized to insert an answer.");
            log.error("You are not authorized to insert an answer.");
        }
    }

    @Override
    @Transactional
    public void deleteAnswer(Answer answer) {
        if (answerMapper.isAdminRole(answer.getMemberId())) {
            Long questionId = answerMapper.getQuestionId(answer.getAnswerId());
            questionMapper.updateToggleQuestionResponseStatus(questionId);
            answerMapper.deleteAnswer(answer.getAnswerId());
        }else{
//            throw new UnauthorizedException("You are not authorized to delete an answer.");
            log.error("You are not authorized to delete an answer.");
        }
    }

    @Override
    @Transactional
    public void updateAnswer(Answer answer) {
        if (answerMapper.isAdminRole(answer.getMemberId())) {
            answerMapper.updateAnswer(answer);
        }else{
//            throw new UnauthorizedException("You are not authorized to update an answer.");
            log.error("You are not authorized to update an answer.");
        }
    }
}
