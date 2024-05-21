package com.ssafy.enjoytrip.domain.hotplace.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotPlaceSearchCond {
    private Long memberId;
    private int pgno;
    private int spp;
    private String key;
    private String word;

}
