package com.fp.finpoint.domain.invest.service;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.entity.InvestDto;
import com.fp.finpoint.domain.invest.repository.InvestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestService {

    private final InvestRepository investRepository;

    public List<Invest> getList() {
        return this.investRepository.findAll();
    }

    public void create(String subject, String content) {
//            Invest i = new Invest();
//            i.setSubject(subject);
//            i.setContent(content);
//            this.investRepository.save(i);
        InvestDto investDto = new InvestDto(subject, content);
        investRepository.save(investDto.toEntity());
    }
}
