package com.ssafy.enjoytrip.domain.itinerary.controller;

import com.ssafy.enjoytrip.domain.itinerary.model.Itinerary;
import com.ssafy.enjoytrip.domain.itinerary.model.ItineraryDetail;
import com.ssafy.enjoytrip.domain.itinerary.model.dto.ItineraryOverviewDto;
import com.ssafy.enjoytrip.domain.itinerary.service.ItineraryService;
import com.ssafy.enjoytrip.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/itinerary")
public class ItineraryController {
    private final ItineraryService itineraryService;

    @PostMapping
    public ApiResponse<?> insertItinerary(@RequestBody Itinerary itinerary) {
        log.debug("Inserting itinerary: {}", itinerary);
        try {
            itineraryService.insertItinerary(itinerary);
            Long itineraryId = itineraryService.findLastItineraryId(itinerary.getMemberId());
            updateItineraryDetails(itinerary.getItineraryDetailList(), itineraryId);
            return ApiResponse.createSuccessWithNoContent();
        } catch (Exception e) {
            log.error("Failed to insert itinerary: {}", e.getMessage());
            return ApiResponse.createError(e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse<?> getItineraryList() {
        log.debug("Fetching all itineraries");
        try {
            List<Itinerary> list = itineraryService.getItineraryList();

            if (list != null && !list.isEmpty()) {
                populateItineraryDetails(list);
                return ApiResponse.createSuccess(list);
            } else {
                return ApiResponse.createFail("No Content");
            }
        } catch (Exception e) {
            log.error("Failed to fetch itinerary list: {}", e.getMessage());
            return ApiResponse.createError(e.getMessage());
        }
    }

    @GetMapping("/{memberId}")
    public ApiResponse<?> findItineraryList(@PathVariable("memberId") Long memberId) {
        log.debug("Fetching itineraries for memberId: {}", memberId);
        try {
            List<Itinerary> list = itineraryService.findItineraryList(memberId);
            if (list != null && !list.isEmpty()) {
                populateItineraryDetails(list);
                return ApiResponse.createSuccess(list);
            } else {
                return ApiResponse.createFail("No Content");
            }
        } catch (Exception e) {
            log.error("Failed to fetch itineraries for memberId {}: {}", memberId, e.getMessage());
            return ApiResponse.createError(e.getMessage());
        }
    }

    @GetMapping("/overview")
    public ApiResponse<?> getItineraryOverview() {
        try {
            List<ItineraryOverviewDto> itineraryOverviewList = itineraryService.getItineraryOverviewList();
            return ApiResponse.createSuccess(itineraryOverviewList);
        } catch (Exception e) {
            log.error("Failed to fetch itineraryOverviewList: {}", e.getMessage());
            return ApiResponse.createError(e.getMessage());
        }
    }

    @DeleteMapping
    public ApiResponse<?> deleteItinerary(@RequestParam("itineraryId") Long itineraryId, @RequestParam("memberId") Long memberId) {
        try {
            itineraryService.deleteItinerary(itineraryId, memberId);
            return ApiResponse.createSuccessWithNoContent();
        } catch (Exception e) {
            log.error("Failed to delete itinerary: {}", e.getMessage());
            return ApiResponse.createError(e.getMessage());
        }
    }

    private void updateItineraryDetails(List<ItineraryDetail> details, Long itineraryId) {
        int idx = 1;
        if (details != null && !details.isEmpty()) {
            for (ItineraryDetail detail : details) {
                detail.setItineraryOrder(idx++);
                detail.setItineraryId(itineraryId);
                itineraryService.insertItineraryDetail(detail);
            }
        }
    }

    private void populateItineraryDetails(List<Itinerary> itineraries) {
        for (Itinerary itinerary : itineraries) {
            Long itineraryId = itinerary.getItineraryId();
            List<ItineraryDetail> details = itineraryService.getItineraryDetailList(itineraryId);
            itinerary.setItineraryDetailList(details);
        }
    }
}
