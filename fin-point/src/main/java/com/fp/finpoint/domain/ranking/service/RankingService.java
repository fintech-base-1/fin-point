package com.fp.finpoint.domain.ranking.service;

import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.global.util.CookieUtil;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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



}
