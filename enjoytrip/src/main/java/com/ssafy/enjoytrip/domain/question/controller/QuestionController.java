package com.ssafy.enjoytrip.domain.question.controller;

import com.ssafy.enjoytrip.domain.question.model.Question;
import com.ssafy.enjoytrip.domain.question.model.dto.SearchCondition;
import com.ssafy.enjoytrip.domain.question.service.QuestionService;
import com.ssafy.enjoytrip.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("")
    public ApiResponse<?> listQuestions() {
        try {
            List<Question> questions = questionService.listQuestion();
            return ApiResponse.createSuccess(questions);
        } catch (Exception e) {
            log.error("Failed to retrieve questions", e);
            return ApiResponse.createError("질문글 목록 조회 실패");
        }
    }

    @GetMapping("/{questionId}")
    public ApiResponse<?> getQuestion(@PathVariable Long questionId) {
        try {
            Question question = questionService.questionInfo(questionId);
            return ApiResponse.createSuccess(question);
        } catch (Exception e) {
            log.error("Failed to retrieve question with ID: {}", questionId, e);
            return ApiResponse.createError("질문글 상세 조회 실패");
        }
    }

    @PostMapping("")
    public ApiResponse<?> createQuestion(@RequestBody Question question) {
        try {
            questionService.insertQuestion(question);
            return ApiResponse.createSuccessWithNoContent();
        } catch (Exception e) {
            log.error("Failed to create question", e);
            return ApiResponse.createError("질문글 생성 실패");
        }
    }

    @PatchMapping("/{questionId}")
    public ApiResponse<?> updateQuestion(@PathVariable Long questionId, @RequestBody Question question) {
        try {
            question.setQuestionId(questionId);
            questionService.updateQuestion(question);
            return ApiResponse.createSuccessWithNoContent();
        } catch (Exception e) {
            log.error("Failed to update question with ID: {}", questionId, e);
            return ApiResponse.createError("질문글 수정 실패");
        }
    }

    @DeleteMapping("/{questionId}")
    public ApiResponse<?> deleteQuestion(@PathVariable Long questionId) {
        try {
            questionService.deleteQuestion(questionId);
            return ApiResponse.createSuccessWithNoContent();
        } catch (Exception e) {
            log.error("Failed to delete question with ID: {}", questionId, e);
            return ApiResponse.createError("질문글 삭제 실패");
        }
    }

    @GetMapping("/search")
    public ApiResponse<?> searchQuestions(@ModelAttribute SearchCondition condition) {
        try {
            List<Question> questions = questionService.searchQuestion(condition);
            return ApiResponse.createSuccess(questions);
        } catch (Exception e) {
            log.error("Failed to search questions", e);
            return ApiResponse.createError("질문글 조건 검색 실패");
        }
    }
}
