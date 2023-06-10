package com.fp.finpoint.domain.file.service;


import com.fp.finpoint.domain.file.entity.FileEntity;
import com.fp.finpoint.domain.file.repository.FileRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${file.dir}") // file: dir: c:/test/ 같이 yml 추가 및 경로에 폴더 필요
    private String fileDirectory;
    private final FileRepository fileRepository;

    public Long saveFile(MultipartFile files) throws IOException {
        if (files.isEmpty()) {
            return null;
        }
        String origName = files.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = origName.substring(origName.lastIndexOf("."));
        String savedName = uuid + extension;
        String savedPath = fileDirectory + savedName;
        FileEntity file = FileEntity.builder()
                .originName(origName)
                .savedName(savedName)
                .savedPath(savedPath)
                .build();
        files.transferTo(new File(savedPath));
        FileEntity savedFile = fileRepository.save(file);
        return savedFile.getId();
    }


}
