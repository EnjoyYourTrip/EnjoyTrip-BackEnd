package com.ssafy.enjoytrip.domain.hotplace.service;

import com.ssafy.enjoytrip.domain.hotplace.mapper.HotPlaceMapper;
import com.ssafy.enjoytrip.domain.hotplace.model.HotPlace;
import com.ssafy.enjoytrip.domain.hotplace.model.HotPlaceList;
import com.ssafy.enjoytrip.domain.hotplace.model.dto.HotPlaceListResponse;
import com.ssafy.enjoytrip.domain.hotplace.model.dto.HotPlaceSearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class HotPlaceServiceImpl implements HotPlaceService {
    private final HotPlaceMapper hotPlaceMapper;

    @Override
    public void write(HotPlace hotPlace) {
        hotPlaceMapper.write(hotPlace);
    }

    @Override
    public void writeFile(Map<String, Object> params) {
        hotPlaceMapper.writeFile(params);
    }

    @Override
    public HotPlaceList hotPlaceList(HotPlaceSearchCond hotPlaceSearchCond) {
        Map<String, Object> param = new HashMap<>();
        param.put("memberId", hotPlaceSearchCond.getMemberId());
        param.put("word", hotPlaceSearchCond.getWord() == null ? "" : hotPlaceSearchCond.getWord());
        int currentPage = hotPlaceSearchCond.getPgno();
        int sizePerPage = hotPlaceSearchCond.getSpp();
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);

        String key = hotPlaceSearchCond.getKey();
        param.put("key", key == null ? "" : key);
        System.out.println("key " + key);

        List<HotPlaceListResponse> list = hotPlaceMapper.hotPlaceList(param);

        int totalArticleCount = hotPlaceMapper.getTotalCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        HotPlaceList hotPlaceList = new HotPlaceList();
        hotPlaceList.setHotplaces(list);
        hotPlaceList.setCurrentPage(currentPage);
        hotPlaceList.setTotalPages(totalPageCount);

        return hotPlaceList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotPlace> hotPlaceTOP3() {
        return hotPlaceMapper.hotPlaceTOP3();
    }

    @Override
    @Transactional(readOnly = true)
    public HotPlace detail(Long hotplaceId) {
        return hotPlaceMapper.detail(hotplaceId);
    }

    @Override
    public void delete(Long hotplaceId) {
        hotPlaceMapper.delete(hotplaceId);
    }

    @Override
    public void modify(HotPlace hotPlace) {
        hotPlaceMapper.modify(hotPlace);
    }

    @Override
    @Transactional(readOnly = true)
    public int getHeartCount(Long hotplaceId) {
        return hotPlaceMapper.getHeartCount(hotplaceId);
    }

    @Override
    public List<Long> getMyHeart(Long memberId) {
        return hotPlaceMapper.getMyHeartList(memberId);
    }

    @Override
    public void changeHeartState(Long hotplaceId, Long memberId) {
        Map<String, Object> param = new HashMap<>();
        param.put("memberId", memberId);
        param.put("hotplaceId", hotplaceId);

        // 핫플 누른 수가 없으면
        if (hotPlaceMapper.checkHeart(param) == 0) {
            hotPlaceMapper.increaseHeartCount(hotplaceId);
            hotPlaceMapper.insertHeartHotPlace(param);
        } else {
            hotPlaceMapper.decreaseHeartCount(hotplaceId);
            hotPlaceMapper.deleteHeartHotPlace(param);
        }
    }

    @Override
    public List<HotPlace> getMyHeartList(Long memberId) {
        List<Long> list = hotPlaceMapper.getMyHeartList(memberId);
        List<HotPlace> result = new ArrayList<>();

        for (Long hotplaceId: list) {
            HotPlace hotPlace = hotPlaceMapper.detail(hotplaceId);
            result.add(hotPlace);
        }
        return result;
    }
}
