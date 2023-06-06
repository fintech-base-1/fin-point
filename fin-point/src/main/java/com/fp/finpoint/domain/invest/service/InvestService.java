package com.fp.finpoint.domain.invest.service;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.entity.InvestDto;
import com.fp.finpoint.domain.invest.repository.InvestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvestService {

    private final InvestRepository investRepository;

    //게시글 리스트.
    public List<Invest> getInvestList() {
        return this.investRepository.findAll();
    }

    // 특정 게시글.
    public Invest investDetail(Long id) {
        return investRepository.findById(id).orElseThrow(() -> new RuntimeException("error"));
    }

    //게시글 생성.
    public void create(String subject, String content , Long id) {
        InvestDto investDto = new InvestDto(subject, content, id);
        investRepository.save(investDto.toEntity());
    }

    public void save(InvestDto investDto) {
        investRepository.save(investDto.toEntity()).getId();
    }

    //게시글 삭제.
    public void deleteInvest(Long id) {
        investRepository.deleteById(id);
    }



    public void update(InvestDto investDto){

        investRepository.save(investDto.toEntity());
    }


}
