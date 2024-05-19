package com.ssafy.enjoytrip.domain.hotplace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private String saveFolder;
    private String originalFile;
    private String saveFile;
}
