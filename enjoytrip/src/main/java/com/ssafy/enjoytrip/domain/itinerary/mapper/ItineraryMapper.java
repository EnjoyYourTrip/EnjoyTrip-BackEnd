package com.ssafy.enjoytrip.domain.itinerary.mapper;

import com.ssafy.enjoytrip.domain.itinerary.model.ItineraryDetail;
import com.ssafy.enjoytrip.domain.itinerary.model.Itinerary;
import com.ssafy.enjoytrip.domain.itinerary.model.dto.ItineraryOverviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItineraryMapper {
    void insertItinerary(Itinerary itinerary);

    void deleteItinerary(@Param("itineraryId") Long itineraryId, @Param("memberId") Long memberId);

    Long findLastItineraryId(Long memberId);

    List<Itinerary> getItineraryList();

    List<Itinerary> findItineraryList(Long memberId);

    void insertItineraryDetail(ItineraryDetail itineraryDetail);

    void deleteItineraryDetail(Long itineraryId);

    List<ItineraryDetail> getItineraryDetailList(Long itineraryId);

    List<ItineraryOverviewDto> getItineraryOverviewList();
}
