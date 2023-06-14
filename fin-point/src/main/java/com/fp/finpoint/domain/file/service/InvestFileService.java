package com.fp.finpoint.domain.file.service;

import com.fp.finpoint.domain.file.entity.FileEntity;
import com.fp.finpoint.domain.file.repository.FileRepository;
import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.repository.InvestRepository;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import com.fp.finpoint.global.util.CookieUtil;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

    @Slf4j
    @RequiredArgsConstructor
    @Service
    public class InvestFileService {

        @Value("${file.dir}") // file: dir: c:/test/ 같이 yml 추가 및 경로에 폴더 필요
        private String fileDirectory;
        private final FileRepository fileRepository;
        private final MemberRepository memberRepository;
        private final InvestRepository investRepository;

        @Transactional
        public Long saveFile(MultipartFile files) throws IOException {
            if (files.isEmpty()) {
                return null;
            }
            String originName = files.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String extension = originName.substring(originName.lastIndexOf("."));
            String savedName = uuid + extension;
            String savedPath = fileDirectory + savedName;
            FileEntity file = FileEntity.builder()
                    .originName(originName)
                    .savedName(savedName)
                    .savedPath(savedPath)
                    .build();
            files.transferTo(new File(savedPath));
            FileEntity savedFile = fileRepository.save(file);

            return savedFile.getId();
        }

        public Resource getInvestImageUrl(Long id) throws MalformedURLException {
            Invest invest = investRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.INVEST_NOT_FOUND));
//            System.out.println("invest.getFileEntity().getId() : 확인확인확인확인 인식"+ invest.getFileEntity().getId());
            FileEntity file = fileRepository.findById(invest.getFileEntity().getId())
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.VALUE_NOT_FOUND));
            Resource a =  new UrlResource("file:" + file.getSavedPath());
            System.out.println("확인확인확인확인 인식 : "+a);
            return a;
        }

    }

