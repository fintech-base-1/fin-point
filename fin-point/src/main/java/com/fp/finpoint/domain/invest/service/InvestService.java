package com.fp.finpoint.domain.invest.service;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.entity.InvestDto;
import com.fp.finpoint.domain.invest.repository.InvestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvestService {

    private final InvestRepository investRepository;

    //게시글 리스트.
    public List<Invest> getInvestList() {
        return investRepository.findAll();
    }

    //특정 게시글 상세
    public Invest getInvestListDetail(Long id) {

        return investRepository.findById(id).get();
    }

    //게시글 생성.
    public void create(InvestDto investDto) {
//            Invest i = new Invest();
//            i.setSubject(subject);
//            i.setContent(content);
//            this.investRepository.save(i);
//        InvestDto investDto = new InvestDto(subject, content, id);
        investRepository.save(investDto.toEntity());
    }

    //게시글 생성 후 저장
    public void save(InvestDto investDto) {

        investRepository.save(investDto.toEntity()).getId();
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
        return investRepository.findByTitleContaining(SearchKeyword,pageable);

    }

}
