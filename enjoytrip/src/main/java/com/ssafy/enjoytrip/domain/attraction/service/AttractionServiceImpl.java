package com.ssafy.enjoytrip.domain.attraction.service;

import com.ssafy.enjoytrip.domain.attraction.mapper.AttractionMapper;
import com.ssafy.enjoytrip.domain.attraction.model.AttractionInfo;
import com.ssafy.enjoytrip.domain.attraction.model.Gugun;
import com.ssafy.enjoytrip.domain.attraction.model.Sido;
import com.ssafy.enjoytrip.domain.attraction.model.dto.AttractionInfoResponse;
import com.ssafy.enjoytrip.domain.attraction.model.dto.AttractionSearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    private final AttractionMapper attractionMapper;

    @Override
    public List<AttractionInfo> attractionList(AttractionSearchCond attractionSearchCond) {

//        if (attractionSearchCond.getTitle() == null) {
//            attractionSearchCond.setTitle("");
//        }
        return attractionMapper.attractionList(attractionSearchCond);
    }

    @Override
    public List<Gugun> gugunList(int sidoCode) {
        return attractionMapper.gugunList(sidoCode);
    }

    @Override
    public List<Sido> sidoList() {
        return attractionMapper.sidoList();
    }

    @Override
    public String getOverview(int contentId) {
        return attractionMapper.getOverview(contentId);
    }

    @Override
    public AttractionInfoResponse getAttraction(int contentId) {
        return attractionMapper.getAttraction(contentId);
    }
}


