package com.ssafy.enjoytrip.domain.attraction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionSearchCond {
    private int sidoCode;
    private int gugunCode;
    private int contentTypeId;
    private String title;
}
