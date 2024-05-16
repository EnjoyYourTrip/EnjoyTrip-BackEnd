package com.ssafy.enjoytrip.domain.itinerary.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItineraryDetail {
    private Long itineraryDetailId;
    private Long itineraryId;
    private int itineraryOrder;
    private int contentId;

    private ItineraryDetail(Long itineraryId, int itineraryOrder, int contentId) {
        this.itineraryId = itineraryId;
        this.itineraryOrder = itineraryOrder;
        this.contentId = contentId;
    }

    public static ItineraryDetail createItineraryDetail(Long itineraryId, int itineraryOrder, int contentId) {
        return new ItineraryDetail(itineraryId, itineraryOrder, contentId);
    }
}
