package com.fp.finpoint.domain.file.service;

import com.fp.finpoint.domain.file.entity.FileEntity;
import com.fp.finpoint.domain.file.repository.FileRepository;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.global.util.CookieUtil;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${file.dir}") // file: dir: c:/test/ 같이 yml 추가 및 경로에 폴더 필요
    private String fileDirectory;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;

    public Long saveFile(MultipartFile files, HttpServletRequest request) throws IOException {
        if (files.isEmpty()) {
            return null;
        }
        String originName = files.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = originName.substring(originName.lastIndexOf("."));
        String savedName = uuid + extension;
        String savedPath = fileDirectory + savedName;
        String email = CookieUtil.getEmailToCookie(request);


        FileEntity existingFile = fileRepository.findByEmail(email);
        if (existingFile != null) {
            existingFile.setOriginName(originName);
            existingFile.setSavedName(savedName);
            existingFile.setSavedPath(savedPath);
            files.transferTo(new File(savedPath));
            FileEntity savedFile = fileRepository.save(existingFile);
            return savedFile.getId();
        }
        FileEntity file = FileEntity.builder()
                .originName(originName)
                .savedName(savedName)
                .savedPath(savedPath)
                .build();
        files.transferTo(new File(savedPath));
        FileEntity savedFile = fileRepository.save(file);
        return savedFile.getId();
    }

//    public Resource getImageUrl(HttpServletRequest request) throws MalformedURLException {
//        String email = CookieUtil.getEmailToCookie(request);
//        Optional<Member> member = memberRepository.findByEmail(email);
//        return new UrlResource("file:"+file.getSavedPath());
//
//    }

}
