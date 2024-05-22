package com.ssafy.enjoytrip.domain.hotplace.controller;

import com.ssafy.enjoytrip.domain.hotplace.model.HotPlace;
import com.ssafy.enjoytrip.domain.hotplace.model.HotPlaceList;
import com.ssafy.enjoytrip.domain.hotplace.model.dto.HotPlaceRegisterRequest;
import com.ssafy.enjoytrip.domain.hotplace.model.dto.HotPlaceSearchCond;
import com.ssafy.enjoytrip.domain.hotplace.service.HotPlaceService;
import com.ssafy.enjoytrip.util.ApiResponse;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hotplace")
public class HotPlaceController {
    private final HotPlaceService hotPlaceService;
    private final ServletContext servletContext;

    @Value("${file.upload-dir}")
    private String uploadDir;

    // 파일 제외하고 핫플레이스 등록
    @PostMapping("/write")
    public ApiResponse<?> write(@RequestBody HotPlaceRegisterRequest hotPlaceRegisterRequest) {
        log.debug("write hotPlaceRegisterRequest : {}", hotPlaceRegisterRequest);
        try {
            hotPlaceService.write(hotPlaceRegisterRequest);
            return ApiResponse.createSuccess(hotPlaceRegisterRequest.getHotplaceId());
        } catch (Exception e) {
            return ApiResponse.createError("파일 제외 핫플 write 실패");
        }
    }


    // 핫플레이스에 맞는 파일 등록
    @PostMapping("/write/file")
    public ApiResponse<?> writeFile(@RequestParam("hotplaceId") Long hotplaceId,
                                    @RequestParam("uploadFile") MultipartFile file) {
        log.debug("write hotplaceDto : {}", file);
        try {
            log.debug("MultipartFile.isEmpty : {}", file.isEmpty());
            if (!file.isEmpty()) {
                String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String saveFolder = uploadDir + File.separator + today;
                log.debug("저장 폴더-------------------------- : {}", saveFolder);
                File folder = new File(saveFolder);
                if (!folder.exists())
                    folder.mkdirs();
                String originalFileName = file.getOriginalFilename();
                if (!originalFileName.isEmpty()) {
                    String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf('.'));
                    File destFile = new File(folder, saveFileName);

                    Map<String, Object> params = new HashMap<>();
                    params.put("hotplaceId", hotplaceId);
                    params.put("saveFolder", today);
                    params.put("saveFile", saveFileName);
                    params.put("originalFile", originalFileName);
                    log.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", file.getOriginalFilename(), saveFileName);

                    // 파일 전송
                    file.transferTo(destFile);

                    hotPlaceService.writeFile(params);
                }
                return ApiResponse.createSuccess(hotplaceId, "파일만 핫플 write 성공");
            } else {
                return ApiResponse.createFail("파일이 없습니다");
            }
        } catch (Exception e) {
            log.error("에러 로그입니다        {}", e.getMessage());
            return ApiResponse.createError("파일만 핫플 write 실패");
        }
    }


    // 핫플 목록 전체 조회
    @PostMapping("/list")
    public ApiResponse<?> list(@RequestBody HotPlaceSearchCond hotPlaceSearchCond, HttpServletResponse response) {
        log.debug("key : {} ", hotPlaceSearchCond.getKey());
        HotPlaceList list;
        try {
            list = hotPlaceService.hotPlaceList(hotPlaceSearchCond);
            response.setContentType("application/json; charset=UTF-8");
            return ApiResponse.createSuccess(list);
        } catch (Exception e) {
            return ApiResponse.createError("핫플목록 가져오기 실패");
        }
    }

    // 핫플 찜TOP 3
    @GetMapping("/top3")
    public ApiResponse<?> top3() {
        List<HotPlace> list;
        try {
            list = hotPlaceService.hotPlaceTOP3();
            if (list != null) {
                return ApiResponse.createSuccess(list, " top3 가져오기 성공");
            } else {
                return ApiResponse.createFail("top3 핫플 목록 없음");
            }
        } catch (Exception e) {
            return ApiResponse.createError("핫플목록 가져오기 실패");
        }
    }

    // 핫플 상세보기
    @GetMapping("/detail/{hotplaceId}")
    public ApiResponse<?> detail(@PathVariable Long hotplaceId) {
        log.debug("detail hotplaceId : {}", hotplaceId);
        HotPlace hotPlace = null;
        try {
            hotPlace = hotPlaceService.detail(hotplaceId);
            return ApiResponse.createSuccess(hotPlace);
        } catch (Exception e) {
            return ApiResponse.createError("핫플 가져오기 실패");
        }
    }


    // 핫플 수정
    @PutMapping("/modify")
    public ApiResponse<?> modify(@RequestBody HotPlace hotPlace) {
        log.debug("modify hotplace : {}", hotPlace);
        try {
            hotPlaceService.modify(hotPlace);
            return ApiResponse.createSuccess(hotPlace.getHotplaceId(), "핫플 수정 성공");
        } catch (Exception e) {
            return ApiResponse.createError("핫플 수정 실패");
        }
    }

    // 핫플 삭제
    @DeleteMapping("/delete/{hotplaceId}")
    public ApiResponse<?> delete(@PathVariable Long hotplaceId) {
        log.debug("delete hotplaceId : {}", hotplaceId);
        try {
            hotPlaceService.delete(hotplaceId);
            return ApiResponse.createSuccess(hotplaceId, "핫플 삭제 성공");
        } catch (Exception e) {
            return ApiResponse.createError("핫플 삭제 실패");
        }
    }

    // 핫플 찜 수 가져오기
    @GetMapping("/getHeart/{hotplaceId}")
    public ApiResponse<?> getHeart(@PathVariable Long hotplaceId) {
        log.debug("heart hotplace : {}", hotplaceId);
        try {
            int count = hotPlaceService.getHeartCount(hotplaceId);
            return ApiResponse.createSuccess(count, "추천수");
        } catch (Exception e) {
            return ApiResponse.createError("추천수 가져오기 실패");
        }
    }

    // 핫플 찜 하기
    @PostMapping("/heart/{hotplaceId}/{memberId}")
    public ApiResponse<?> toggleHeart(@PathVariable Long hotplaceId, @PathVariable Long memberId) {
        try {
            hotPlaceService.changeHeartState(hotplaceId, memberId);
            return ApiResponse.createSuccess("찜 성공");
        } catch (Exception e) {
            log.error("핫플레이스  찜 실패 : {}", e);
            return ApiResponse.createError("핫플레이스 찜 실패");
        }
    }

    // 찜 목록 조회
    @GetMapping("/myHeartList/{memberId}")
    public ApiResponse<?> getMyRecommendList(@PathVariable Long memberId) {
        try {
            List<HotPlace> myHeartList = hotPlaceService.getMyHeartList(memberId);
            return ApiResponse.createSuccess(myHeartList, "나의 찜 목록 가져오기 성공");
        } catch (Exception e) {
            log.error("나의 찜 목록 가져오기 실패 : {}", e);
            return ApiResponse.createError("나의 찜 목록 가져오기 실패");
        }
    }

    // 찜 누른 pk들 조회
    @GetMapping("/myHeart/{memberId}")
    public ApiResponse<?> getMyHeart(@PathVariable Long memberId) {
        try {
            List<Long> list = hotPlaceService.getMyHeart(memberId);
            return ApiResponse.createSuccess(list, "나의 찜 누른 목록 가져오기 성공");
        } catch (Exception e) {
            log.error("나의 찜 누른 목록 가져오기 실패 : {}", e);
            return ApiResponse.createError("나의 찜 누른 목록 가져오기 실패");
        }
    }
}