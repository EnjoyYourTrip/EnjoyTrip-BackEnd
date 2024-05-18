package com.ssafy.enjoytrip.domain.attraction.service;

import com.ssafy.enjoytrip.domain.attraction.model.AttractionInfo;
import com.ssafy.enjoytrip.domain.attraction.model.Gugun;
import com.ssafy.enjoytrip.domain.attraction.model.Sido;
import com.ssafy.enjoytrip.domain.attraction.model.dto.AttractionInfoResponse;
import com.ssafy.enjoytrip.domain.attraction.model.dto.AttractionSearchCond;

import java.util.List;

public interface AttractionService {
    List<AttractionInfo> attractionList(AttractionSearchCond attractionSearchCond);

    List<Gugun> gugunList(int sidoCode);

    List<Sido> sidoList();

    String getOverview(int contentId);

    AttractionInfoResponse getAttraction(int contentId);
}
