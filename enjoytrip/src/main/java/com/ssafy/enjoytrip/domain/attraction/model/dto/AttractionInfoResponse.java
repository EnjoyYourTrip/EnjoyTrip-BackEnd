package com.ssafy.enjoytrip.domain.attraction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionInfoResponse {
    private int contentId;
    private String title;
    private String addr1;
    private String firstImage;
    private double latitude;
    private double longitude;
}
