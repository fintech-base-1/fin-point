package com.fp.finpoint.domain.invest.dto;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private UploadFile attachFile;
    private List<UploadFile> imageFile; // 이미지가 여래개 일 수 있기 때문에 List.
}
