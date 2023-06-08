package com.fp.finpoint.domain.invest.service;

import com.fp.finpoint.domain.invest.entity.DataNotFoundException;
import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.entity.InvestDto;
import com.fp.finpoint.domain.invest.repository.InvestRepository;
import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvestService {

    private final InvestRepository investRepository;
    private final MemberRepository memberRepository;

    //게시글 리스트.
    public List<Invest> getInvestList() {

        return this.investRepository.findAll();
    }

    // 특정 게시글.
    public Invest investDetail(Long id) {
        return investRepository.findById(id).orElseThrow(() -> new RuntimeException("error"));
    }

    //게시글 생성.
//    public void create(String subject, String content , Long id) {
//        InvestDto investDto = new InvestDto(subject, content, id);
//        investRepository.save(investDto.toEntity());
//
//    }
    //게시글 생성.
    public void create(InvestDto investDto, MemberDto memberDto) {
        Member findMember = memberRepository.findByEmail(memberDto.getEmail()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        investDto.setMember(findMember);
//        if(!findMember.isPresent()) {
//            throw new IllegalArgumentException();
//        }

        investRepository.save(investDto.toEntity());

    }


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
        return investRepository.findBySubjectContaining(SearchKeyword,pageable);

    }

//    //좋아요 기능
//    public void liked(Invest invest, Member token){
//        invest.getLiked().add(token);
//        log.info("invest = {]" , invest.getLiked().size());
//        this.investRepository.save(invest);
//    }
//
//    //좋아요 기능
//    public Invest getInvest(long id){
//        Optional<Invest> invest=this.investRepository.findById(id);
//        if(invest.isPresent()) {
//            return invest.get();
//        }else{
//            throw new DataNotFoundException("invest not found");
//        }
//    }





}
