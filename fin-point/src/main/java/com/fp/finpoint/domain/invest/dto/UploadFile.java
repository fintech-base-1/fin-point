package com.fp.finpoint.domain.invest.dto;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName; // 고객이 업로드한 파일.
    private String storeFileName;  // 서버 내부에서 관리하는 파일(서버에 이미 저장된 파일). -> 저장된 이미지와 업로드하는 이미지가 겹칠 수 있기 때문.

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
