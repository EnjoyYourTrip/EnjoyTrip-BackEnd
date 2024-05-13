package com.ssafy.enjoytrip.domain.attraction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDescription {
    private int contentId;
    private String homepage;
    private String overview;
    private String telname;
}
