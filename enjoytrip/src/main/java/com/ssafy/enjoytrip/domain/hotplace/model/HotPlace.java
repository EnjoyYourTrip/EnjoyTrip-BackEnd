package com.ssafy.enjoytrip.domain.hotplace.model;

import com.ssafy.enjoytrip.domain.common.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HotPlace extends BaseTimeEntity {
    private Long hotplaceId;
    private Long memberId;
    private String title;
    private String content;
    private String address;
    private String username;
    private int heart;
    private String selectedFilter;
    private String saveFolder;
    private String originalFile;
    private String saveFile;
}
