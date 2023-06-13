package com.fp.finpoint.domain.ranking.service;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RankingService {

    private final MemberRepository memberRepository;

    public Page<Member> getMemberRankingByPieceCount(int pageNumber){
        Sort sortByPieceCount = Sort.by("totalCount").descending();
        PageRequest pageable = PageRequest.of(pageNumber,5,sortByPieceCount);
        return memberRepository.findAll(pageable);
    }

    public Page<Member> getMemberRankingByAllPiecesPrice(int pageNumber){
        Sort sortByAllPiecesPrice = Sort.by("totalPrice").descending();
        PageRequest pageable = PageRequest.of(pageNumber,5,sortByAllPiecesPrice);
        return memberRepository.findAll(pageable);
    }

    public Page<MemberDto> getMemberRankingByAllPiecesPriceTest(int page){
//        Sort sortByAllPiecesPrice = Sort.by("totalPrice").descending();
//        PageRequest pageable = PageRequest.of(page,5,sortByAllPiecesPrice);
        List<MemberDto> members = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String email = "test" + (i + 1) + "@test.com";
            String nickname = "테스트" + (i + 1);
            Integer totalPrice =  (1000000-10000*i);
            MemberDto memberDto = new MemberDto();
            memberDto.setEmail(email);
            memberDto.setNickname(nickname);
            memberDto.setTotalPrice(totalPrice);
            memberDto.setImage("default.jpg");
            members.add(memberDto);
        }
        int pageSize = 5;
        int totalElements = members.size();
        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalElements);
        start = Math.min(start, totalElements);
        end = Math.min(end, totalElements);
        List<MemberDto> content = members.subList(start, end);
        Page<MemberDto> memberPage = new PageImpl<>(content, PageRequest.of(page, pageSize), totalElements);
        return memberPage;
    }



}
