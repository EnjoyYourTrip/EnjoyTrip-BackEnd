package com.ssafy.enjoytrip.domain.attraction.mapper;

import com.ssafy.enjoytrip.domain.attraction.model.AttractionInfo;
import com.ssafy.enjoytrip.domain.attraction.model.Gugun;
import com.ssafy.enjoytrip.domain.attraction.model.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionMapper {
    List<AttractionInfo> attractionList(Map<String, Object> map);

    List<Gugun> gugunList(int sidoCode);

    List<Sido> sidoList();

    AttractionInfo getAttraction(int contentId);

    String getOverview(int contentId);

}
