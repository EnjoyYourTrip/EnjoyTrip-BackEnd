package com.ssafy.enjoytrip.domain.attraction.controller;

import com.ssafy.enjoytrip.domain.attraction.model.AttractionDescription;
import com.ssafy.enjoytrip.domain.attraction.model.AttractionInfo;
import com.ssafy.enjoytrip.domain.attraction.model.Gugun;
import com.ssafy.enjoytrip.domain.attraction.model.Sido;
import com.ssafy.enjoytrip.domain.attraction.service.AttractionService;
import com.ssafy.enjoytrip.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/attraction")
public class AttractionController {

    private final AttractionService attractionService;

    @PostMapping("/list")
    public ApiResponse<?> attractionList(@RequestBody AttractionInfo attractionInfo) {
        try {
            List<AttractionInfo> list = attractionService.attractionList(attractionInfo);
            if (list != null && !list.isEmpty()) {
                return ApiResponse.createSuccess(list);
            } else {
                return ApiResponse.createFail("관광지 리스트 조회 실패");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ApiResponse.createError("관광지 리스트 조회 에러");
        }
    }

    @GetMapping("/gugun/{sidoCode}")
    public ApiResponse<?> gugunList(@PathVariable int sidoCode) {
        try {
            List<Gugun> list = attractionService.gugunList(sidoCode);
            if (list != null && !list.isEmpty()) {
                return ApiResponse.createSuccess(list);
            } else {
                return ApiResponse.createFail("시군에 맞는 구군 목록 조회 실패");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ApiResponse.createError("시군에 맞는 구군 목록 조회 에러");
        }
    }

    @GetMapping("/sido")
    public ApiResponse<?> sidoList() {
        try {
            List<Sido> list = attractionService.sidoList();
            if (list != null && !list.isEmpty()) {
                return ApiResponse.createSuccess(list);
            } else {
                return ApiResponse.createFail("구군 목록 조회 실패");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ApiResponse.createError("시군 목록 조회 에러");
        }
    }

    @GetMapping("/overview/{contentId}")
    public ApiResponse<?> getOverview(@PathVariable int contentId) {
        AttractionDescription attractionDescription;
        try {
            attractionDescription = attractionService.getOverview(contentId);
            return ApiResponse.createSuccess(attractionDescription);
        } catch (Exception e) {
            return ApiResponse.createError("상세정보 조회 에러");
        }
    }

    @GetMapping("/{contentId}")
    public ApiResponse<?> getAttraction(@PathVariable int contentId) {
        try {
            AttractionInfo attractionInfo = attractionService.getAttraction(contentId);
            if (attractionInfo != null) {
                return ApiResponse.createSuccess(attractionInfo);
            } else {
                return ApiResponse.createFail("관광지 조회 실패");
            }
        } catch (Exception e) {
            return ApiResponse.createError("시군 조회 에러");
        }
    }
}