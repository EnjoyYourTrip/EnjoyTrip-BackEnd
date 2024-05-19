package com.ssafy.enjoytrip.domain.hotplace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotPlaceSearchCond {
    private int pgno;
    private int spp;
    private String key;
    private String word;

}
