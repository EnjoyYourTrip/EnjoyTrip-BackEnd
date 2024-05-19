package com.ssafy.enjoytrip.domain.hotplace.mapper;

import com.ssafy.enjoytrip.domain.hotplace.model.HotPlace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HotPlaceMapper {
    int getCount();

    void write(HotPlace hotPlace);

    void writeFile(Map<String, Object> params);

    List<HotPlace> hotPlaceList(Map<String, Object> param);

    int getTotalCount(Map<String, Object> param);

    List<HotPlace> hotPlaceTOP3();

    HotPlace detail(Long hotplaceId);

    void delete(Long hotplaceId);

    void modify(HotPlace hotPlace);

    int getHeartCount(Long hotplaceId);

    void increaseHeartCount(Long hotplaceId);

    void decreaseHeartCount(Long hotplaceId);

    void insertHeartHotPlace(Map<String, Object> param);

    void deleteHeartHotPlace(Map<String, Object> param);

    int checkHeart(Map<String, Object> param);

    List<Long> getMyHeartList(Long memberId);
}
