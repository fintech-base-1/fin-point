package com.fp.finpoint.web.invest.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm { // 이미지 저장용 폼.

    private Long itemId;
    private String itemName;
    private List<MultipartFile> imageFiles; // 이미지 다중 업로드용.
    private MultipartFile attachFile; // MultipartFile 는 @ModelAttribute 에서 사용가능함.
}
