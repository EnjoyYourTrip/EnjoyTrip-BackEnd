package com.ssafy.enjoytrip.domain.itinerary.model;

import com.ssafy.enjoytrip.domain.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary extends BaseTimeEntity {

    private Long itineraryId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String content;
    private Long memberId;
}