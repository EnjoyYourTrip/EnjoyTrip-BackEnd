package com.ssafy.enjoytrip.domain.hotplace.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotPlaceRegisterRequest {
    private Long hotplaceId;
    private Long memberId;
    private String title;
    private String content;
    private String address;
    private String selectedFilter="basic";
}
