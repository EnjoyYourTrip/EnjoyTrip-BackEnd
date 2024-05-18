package com.ssafy.enjoytrip.domain.attraction.mapper;

import com.ssafy.enjoytrip.domain.attraction.model.AttractionInfo;
import com.ssafy.enjoytrip.domain.attraction.model.Gugun;
import com.ssafy.enjoytrip.domain.attraction.model.Sido;
import com.ssafy.enjoytrip.domain.attraction.model.dto.AttractionInfoResponse;
import com.ssafy.enjoytrip.domain.attraction.model.dto.AttractionSearchCond;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionMapper {
    List<AttractionInfo> attractionList(AttractionSearchCond attractionSearchCond);

    List<Gugun> gugunList(int sidoCode);

    List<Sido> sidoList();

    AttractionInfoResponse getAttraction(int contentId);

    String getOverview(int contentId);
}
