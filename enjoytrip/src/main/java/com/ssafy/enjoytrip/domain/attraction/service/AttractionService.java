package com.ssafy.enjoytrip.domain.attraction.service;

import com.ssafy.enjoytrip.domain.attraction.model.AttractionDescription;
import com.ssafy.enjoytrip.domain.attraction.model.AttractionInfo;
import com.ssafy.enjoytrip.domain.attraction.model.Gugun;
import com.ssafy.enjoytrip.domain.attraction.model.Sido;

import java.util.List;

public interface AttractionService {
    List<AttractionInfo> attractionList(AttractionInfo attractionInfoDto);

    List<Gugun> gugunList(int sidoCode);

    List<Sido> sidoList();

    AttractionDescription getOverview(int contentId);

    AttractionInfo getAttraction(int contentId);
}
