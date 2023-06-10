package com.fp.finpoint.domain.file.service;


import com.fp.finpoint.domain.file.entity.FileEntity;
import com.fp.finpoint.domain.file.repository.FileRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${file.dir}")
    private String fileDirectory;

    private final ResourceLoader resourceLoader;

    private final FileRepository fileRepository;

    public Long saveFile(MultipartFile files) throws IOException {

        if (files.isEmpty()) {
            return null;
        }

        // 원래 파일 이름 추출
        String origName = files.getOriginalFilename();

        // 파일 이름으로 쓸 uuid 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출(ex : .png)
        String extension = origName.substring(origName.lastIndexOf("."));

        // uuid와 확장자 결합
        String savedName = uuid + extension;

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDirectory + savedName;

        // 파일 엔티티 생성
        FileEntity file = FileEntity.builder()
                .originName(origName)
                .savedName(savedName)
                .savedPath(savedPath)
                .build();

        // 실제로 로컬에 uuid를 파일명으로 저장
        files.transferTo(new File(savedPath));

        // 데이터베이스에 파일 정보 저장
        FileEntity savedFile = fileRepository.save(file);

        return savedFile.getId();
    }


}
