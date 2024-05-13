package com.ssafy.enjoytrip.domain.attraction.service;

import com.ssafy.enjoytrip.domain.attraction.mapper.AttractionMapper;
import com.ssafy.enjoytrip.domain.attraction.model.AttractionDescription;
import com.ssafy.enjoytrip.domain.attraction.model.AttractionInfo;
import com.ssafy.enjoytrip.domain.attraction.model.Gugun;
import com.ssafy.enjoytrip.domain.attraction.model.Sido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    private final AttractionMapper attractionMapper;

    @Override
    public List<AttractionInfo> attractionList(AttractionInfo attractionInfo) {
        Map<String, Object> map = new HashMap<>();
        int sidocode = attractionInfo.getSidoCode();
        map.put("sidoCode", sidocode);
        int guguncode = attractionInfo.getGugunCode();
        map.put("gugunCode", guguncode);
        int contentTypeId = attractionInfo.getContentTypeId();
        map.put("contentTypeId", contentTypeId);
        String title = attractionInfo.getTitle();
        if (Objects.equals(title, "") || title == null) {
            title = "";
        }
        map.put("title", title);

        return attractionMapper.attractionList(map);
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
    public AttractionDescription getOverview(int contentId) {
        String overview = attractionMapper.getOverview(contentId);
        return new AttractionDescription(contentId, "", overview, "");
    }

    @Override
    public AttractionInfo getAttraction(int contentId) {
        return attractionMapper.getAttraction(contentId);
    }
}


