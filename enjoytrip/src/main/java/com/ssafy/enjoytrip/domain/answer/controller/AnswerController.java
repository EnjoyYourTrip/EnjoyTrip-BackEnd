package com.ssafy.enjoytrip.domain.answer.controller;

import com.ssafy.enjoytrip.domain.answer.model.Answer;
import com.ssafy.enjoytrip.domain.answer.service.AnswerService;
import com.ssafy.enjoytrip.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/{answerId}")
    public ApiResponse<?> getAnswer(@PathVariable Long answerId) {
        try {
            Answer answer = answerService.answerInfo(answerId);
            if (answer == null) {
                return ApiResponse.createFail("답변 상세 조회 실패");
            }
            return ApiResponse.createSuccess(answer);
        } catch (Exception e) {
            log.error("Failed to retrieve answer with ID: {}", answerId, e);
            return ApiResponse.createError("답변 상세 조회 에러");
        }
    }

    @PostMapping("")
    public ApiResponse<?> createAnswer(@RequestBody Answer answer) {
        try {
            answerService.insertAnswer(answer);
            return ApiResponse.createSuccessWithNoContent();
        } catch (Exception e) {
            log.error("Failed to create answer", e);
            return ApiResponse.createError("답변 생성 에러");
        }
    }

    @PatchMapping("/{answerId}")
    public ApiResponse<?> updateAnswer(@PathVariable Long answerId, @RequestBody Answer answer) {
        try {
            answer.setAnswerId(answerId);
            answerService.updateAnswer(answer);
            return ApiResponse.createSuccessWithNoContent();
        } catch (Exception e) {
            log.error("Failed to update answer with ID: {}", answerId, e);
            return ApiResponse.createError("답변 수정 에러");
        }
    }

    @DeleteMapping("/{answerId}")
    public ApiResponse<?> deleteAnswer(@PathVariable Long answerId, @RequestParam Long memberId) {
        try {
            Answer answer = new Answer();
            answer.setAnswerId(answerId);
            answer.setMemberId(memberId);
            answerService.deleteAnswer(answer);
            return ApiResponse.createSuccessWithNoContent();
        } catch (Exception e) {
            log.error("Failed to delete answer with ID: {}", answerId, e);
            return ApiResponse.createError("답변 삭제 에러");
        }
    }
}

