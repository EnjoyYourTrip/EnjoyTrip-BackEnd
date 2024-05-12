package com.ssafy.enjoytrip.domain.question.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {
    private String title; // 검색할 제목
    private String content; // 검색할 내용
    private String order; // 정렬할 기준
    private String direction; // 정렬 방향
}
