package com.ssafy.enjoytrip.domain.page.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 페이지 요청 정보 및 계산된 값
 */
public class PageRequest {

    private int page; // 현재 페이지 번호
    private int size; // 페이지 크기
    @JsonIgnore
    private int limit; // LIMIT 절에 사용될 값
    @JsonIgnore
    private int offset; // OFFSET 절에 사용될 값

    /**
     * offset과 limit 값을 계산
     */
    public void calculateOffset() {
        this.offset = (page - 1) * size; // 현재 페이지 번호를 기반으로 offset 계산
        this.limit = size; // limit은 페이지 크기와 동일
    }
}
