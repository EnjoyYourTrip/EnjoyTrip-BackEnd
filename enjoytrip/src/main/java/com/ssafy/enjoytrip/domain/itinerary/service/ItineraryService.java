package com.ssafy.enjoytrip.domain.itinerary.service;

import com.ssafy.enjoytrip.domain.itinerary.model.Itinerary;
import com.ssafy.enjoytrip.domain.itinerary.model.ItineraryDetail;
import com.ssafy.enjoytrip.domain.itinerary.model.dto.ItineraryOverviewDto;

import java.util.List;

public interface ItineraryService {
    void insertItinerary(Itinerary itinerary);

    Long findLastItineraryId(Long memberId);

    List<Itinerary> getItineraryList();

    List<Itinerary> findItineraryList(Long memberId);

    void insertItineraryDetail(ItineraryDetail itineraryDetail);

    List<ItineraryDetail> getItineraryDetailList(Long itineraryId);

    List<ItineraryOverviewDto> getItineraryOverviewList();

    void deleteItinerary(Long itineraryId, Long memberId);
}
