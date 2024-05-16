package com.ssafy.enjoytrip.domain.itinerary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryOverviewDto {
    private Long itineraryId;
    private String title;
    private LocalDateTime createdDate;
    private String nickname;
    private String firstImage;
}
