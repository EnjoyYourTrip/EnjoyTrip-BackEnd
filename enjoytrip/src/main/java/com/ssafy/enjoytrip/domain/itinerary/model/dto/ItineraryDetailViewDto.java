package com.ssafy.enjoytrip.domain.itinerary.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDetailViewDto {
    private Long itineraryId;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdDate;
    private String nickname;
    private List<Place> places;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Place {
        private int contentId;
        private String title;
        private String addr1;
        private String firstImage;
        private double latitude;
        private double longitude;
    }
}