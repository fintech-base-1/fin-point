package com.fp.finpoint.domain.invest.service;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.entity.InvestDto;
import com.fp.finpoint.domain.invest.repository.InvestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestService {

    private final InvestRepository investRepository;

    //게시글 리스트.
    public List<Invest> getInvestList() {
        return this.investRepository.findAll();
    }

    // 특정 게시글.
    public Invest readInvestDetail(Long id) {
        return investRepository.findById(id).orElseThrow(() -> new RuntimeException("error"));
    }

    //게시글 생성.
    public void create(InvestDto investDto, String subject,  String content, MultipartHttpServletRequest multipartHttpServletRequest) {
        investRepository.save(investDto.toEntity());
    }

    //게시글 삭제.
    public void deleteInvest(Long id) {
        investRepository.deleteById(id);
    }

    //게시글 수정
    public void updateInvest(InvestDto investDto){

        investRepository.save(investDto.toEntity());
    }


    //리스트 페이징
    public Page<Invest> investList(Pageable pageable){
        return investRepository.findAll(pageable);
    }


    //검색 페이징 제목만
    public Page<Invest> investSearchList(String SearchKeyword,Pageable pageable){
        return investRepository.findBySubjectContaining(SearchKeyword,pageable);

    }



}
