package com.ssafy.enjoytrip.domain.attraction.controller;

import com.ssafy.enjoytrip.domain.attraction.model.AttractionInfo;
import com.ssafy.enjoytrip.domain.attraction.model.Gugun;
import com.ssafy.enjoytrip.domain.attraction.model.Sido;
import com.ssafy.enjoytrip.domain.attraction.model.dto.AttractionInfoResponse;
import com.ssafy.enjoytrip.domain.attraction.model.dto.AttractionSearchCond;
import com.ssafy.enjoytrip.domain.attraction.service.AttractionService;
import com.ssafy.enjoytrip.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/attraction")
public class AttractionController {

    private final AttractionService attractionService;

    // 관광지 다중 조건 검색 조회
    @PostMapping("/list")
    public ApiResponse<?> attractionList(@RequestBody AttractionSearchCond attractionSearchCond) {
        try {
            List<AttractionInfo> list = attractionService.attractionList(attractionSearchCond);
            if (!CollectionUtils.isEmpty(list)) {
                return ApiResponse.createSuccess(list);
            } else {
                return ApiResponse.createFail("관광지 리스트 조회 실패");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ApiResponse.createError("관광지 리스트 조회 에러");
        }
    }

    // 시도에 맞는 구군 목록 조회
    @GetMapping("{sidoCode}/gugun")
    public ApiResponse<?> gugunList(@PathVariable int sidoCode) {
        try {
            List<Gugun> list = attractionService.gugunList(sidoCode);
            if (!CollectionUtils.isEmpty(list)) {
                return ApiResponse.createSuccess(list);
            } else {
                return ApiResponse.createFail("시군에 맞는 구군 목록 조회 실패");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ApiResponse.createError("시군에 맞는 구군 목록 조회 에러");
        }
    }

    // 시도 목록 조회
    @GetMapping("/sido")
    public ApiResponse<?> sidoList() {
        try {
            List<Sido> list = attractionService.sidoList();
            if (!CollectionUtils.isEmpty(list)) {
                return ApiResponse.createSuccess(list);
            } else {
                return ApiResponse.createFail("구군 목록 조회 실패");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ApiResponse.createError("시군 목록 조회 에러");
        }
    }

    // 상세 설명 조회
    @GetMapping("/overview/{contentId}")
    public ApiResponse<?> getOverview(@PathVariable int contentId) {
        try {
            String overview = attractionService.getOverview(contentId);

            if (overview != null) {
                return ApiResponse.createSuccess(overview);
            } else {
                return ApiResponse.createFail("No overview provided");
            }
        } catch (Exception e) {
            return ApiResponse.createError("상세정보 조회 에러");
        }
    }

    // 관광지 조회
    @GetMapping("/{contentId}")
    public ApiResponse<?> getAttraction(@PathVariable int contentId) {
        try {
            AttractionInfoResponse attractionInfoResponse = attractionService.getAttraction(contentId);
            if (attractionInfoResponse != null) {
                return ApiResponse.createSuccess(attractionInfoResponse);
            } else {
                return ApiResponse.createFail("관광지 조회 실패");
            }
        } catch (Exception e) {
            return ApiResponse.createError("관광지 조회 에러");
        }
    }
}