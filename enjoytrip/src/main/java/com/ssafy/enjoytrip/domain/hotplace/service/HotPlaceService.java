package com.ssafy.enjoytrip.domain.hotplace.service;

import com.ssafy.enjoytrip.domain.hotplace.model.HotPlace;
import com.ssafy.enjoytrip.domain.hotplace.model.HotPlaceList;
import com.ssafy.enjoytrip.domain.hotplace.model.dto.HotPlaceRegisterRequest;
import com.ssafy.enjoytrip.domain.hotplace.model.dto.HotPlaceSearchCond;

import java.util.List;
import java.util.Map;

public interface HotPlaceService {
    void write(HotPlaceRegisterRequest hotPlaceRegisterRequest);

    void writeFile(Map<String, Object> params);

    HotPlaceList hotPlaceList(HotPlaceSearchCond hotPlaceSearchCond);

    List<HotPlace> hotPlaceTOP3();

    HotPlace detail(Long hotplaceId);

    void delete(Long hotplaceId);

    void modify(HotPlace hotPlace);

    int getHeartCount(Long hotplaceId);

    List<Long> getMyHeart(Long memberId);

    void changeHeartState(Long hotplaceId, Long memberId);

    List<HotPlace> getMyHeartList(Long memberId);
}
