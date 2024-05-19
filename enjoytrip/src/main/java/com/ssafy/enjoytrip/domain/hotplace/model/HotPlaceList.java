package com.ssafy.enjoytrip.domain.hotplace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotPlaceList {

    private List<HotPlace> hotplaces;
    private int totalPages; // 전체 페이지 수
    private int currentPage; // 현재 페이지
}
