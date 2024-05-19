package com.ssafy.enjoytrip.domain.page.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestParam<T> {

    private PageRequest pageRequest; // 페이지 요청 정보 (PageRequest 객체)
    private T param; // 제네릭 타입 T 사용, 추가적인 파라미터를 담기 위해 사용
}
