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


    public Page<Member> getMemberSortByFinpoint(int pageNumber) {
        Sort sortByFinpoint = Sort.by("finpoint").descending();
        PageRequest pageable = PageRequest.of(pageNumber, 5, sortByFinpoint);
        return memberRepository.findAll(pageable);
    }

    public int getMyFinpointRankingByEmail(HttpServletRequest request) {
        List<Member> allMembersSortedByFinpoint = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "finpoint"));
        String email = CookieUtil.getEmailToCookie(request);
        Optional<Member> member = memberRepository.findByEmail(email);
        return allMembersSortedByFinpoint.indexOf(member.get()) + 1;
    }

    public Page<Member> getMemberSortByAssets(int pageNumber) {
        Sort sortByAssets = Sort.by("assets").descending();
        PageRequest pageable = PageRequest.of(pageNumber, 5, sortByAssets);
        return memberRepository.findAll(pageable);
    }

    public int getMyAssetRankingByEmail(HttpServletRequest request) {
        List<Member> allMembersSortedByAssets = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "assets"));
        String email = CookieUtil.getEmailToCookie(request);
        Optional<Member> member = memberRepository.findByEmail(email);
        return allMembersSortedByAssets.indexOf(member.get()) + 1;
    }

}
