package com.ssafy.enjoytrip.domain.hotplace.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotPlaceListResponse {
    private Long hotplaceId;
    private Long memberId;
    private String title;
    private String content;
    private String address;
    private int heart;
    private String selectedFilter;
    private Boolean isHearted;
    private String saveFolder;
    private String originalFile;
    private String saveFile;
}
