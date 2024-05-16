package com.ssafy.enjoytrip.domain.itinerary.service;

import com.ssafy.enjoytrip.domain.itinerary.mapper.ItineraryMapper;
import com.ssafy.enjoytrip.domain.itinerary.model.Itinerary;
import com.ssafy.enjoytrip.domain.itinerary.model.ItineraryDetail;
import com.ssafy.enjoytrip.domain.itinerary.model.dto.ItineraryOverviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItineraryServiceImpl implements ItineraryService {
    private final ItineraryMapper itineraryMapper;

    @Override
    public void insertItinerary(Itinerary itinerary) {
        itineraryMapper.insertItinerary(itinerary);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findLastItineraryId(Long memberId) {
        return itineraryMapper.findLastItineraryId(memberId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Itinerary> getItineraryList() {
        return itineraryMapper.getItineraryList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Itinerary> findItineraryList(Long memberId) {
        return itineraryMapper.findItineraryList(memberId);
    }

    @Override
    public void insertItineraryDetail(ItineraryDetail itineraryDetail) {
        itineraryMapper.insertItineraryDetail(itineraryDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryDetail> getItineraryDetailList(Long itineraryId) {
        return itineraryMapper.getItineraryDetailList(itineraryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryOverviewDto> getItineraryOverviewList() {
        return itineraryMapper.getItineraryOverviewList();
    }

    @Override
    public void deleteItinerary(Long itineraryId, Long memberId) {
        itineraryMapper.deleteItineraryDetail(itineraryId);
        itineraryMapper.deleteItinerary(itineraryId, memberId);
    }
}
