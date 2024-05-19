package com.ssafy.enjoytrip.domain.question.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionListResponse {

    private List<QuestionInfo> questions; // 질문 목록
    private int totalPages; // 전체 페이지 수
    private int currentPage; // 현재 페이지
}