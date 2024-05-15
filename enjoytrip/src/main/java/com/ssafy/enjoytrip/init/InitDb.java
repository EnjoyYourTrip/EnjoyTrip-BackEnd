package com.ssafy.enjoytrip.init;

import com.ssafy.enjoytrip.domain.answer.model.Answer;
import com.ssafy.enjoytrip.domain.answer.service.AnswerService;
import com.ssafy.enjoytrip.domain.member.model.Member;
import com.ssafy.enjoytrip.domain.member.service.MemberService;
import com.ssafy.enjoytrip.domain.question.model.Question;
import com.ssafy.enjoytrip.domain.question.service.QuestionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        log.info("memberInit 시작");
        initService.memberInit();
        log.info("memberInit 종료");

        log.info("questionInit 시작");
        initService.questionInit();
        log.info("questionInit 종료");

        log.info("answerInit 시작");
        initService.answerInit();
        log.info("answerInit 종료");
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {
        private final MemberService memberService;
        private final QuestionService questionService;
        private final AnswerService answerService;

        public void memberInit() {
            for (int i = 2; i <= 6; i++) {
                Member member = Member.createMember("userName" + i, "id" + i, "password" + i, "nickname" + i, "email" + i);
                memberService.insertMember(member);
            }
        }

        public void questionInit() {
            for (int i = 2; i <= 6; i++) {
                for (int j = 1; j <= 5; j++) {
                    Question question = Question.createQuestion(i + "가 쓴 질문제목" + j, "질문내용", Long.parseLong(String.valueOf(i)));
                    questionService.insertQuestion(question);
                }
            }
        }

        public void answerInit() {
            for (int i = 2; i <= 6; i++) {
                Answer answer = Answer.createAnswer(Long.parseLong(String.valueOf(i)), 1L, "답변내용" + (i - 1));
                answerService.insertAnswer(answer);
            }
        }
    }
}

