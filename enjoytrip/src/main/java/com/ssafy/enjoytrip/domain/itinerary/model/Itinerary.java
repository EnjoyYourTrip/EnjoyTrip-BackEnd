package com.ssafy.enjoytrip.domain.itinerary.model;

import com.ssafy.enjoytrip.domain.common.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Itinerary extends BaseTimeEntity {

    private Long itineraryId;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long memberId;
    private List<ItineraryDetail> itineraryDetailList;

    private Itinerary(String title, String content, LocalDate startDate, LocalDate endDate, Long memberId, List<ItineraryDetail> itineraryDetailList) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberId = memberId;
        this.itineraryDetailList = itineraryDetailList;
    }

    public static Itinerary createItinerary(String title, String content, LocalDate startDate, LocalDate endDate, Long memberId, List<ItineraryDetail> itineraryDetailList) {
        return new Itinerary(title, content, startDate, endDate, memberId, itineraryDetailList);
    }
}