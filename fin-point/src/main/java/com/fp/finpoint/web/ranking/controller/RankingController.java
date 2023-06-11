package com.fp.finpoint.web.ranking.controller;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.ranking.service.RankingService;
import com.sun.javadoc.MemberDoc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    /* 랭킹 테스트 데이터 */
    @GetMapping("/ranking")
    public String ranking(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<MemberDto> memberPage = rankingService.getMemberRankingByAllPiecesPriceTest(page);
        model.addAttribute("memberPage", memberPage);
        return "ranking";
    }


//    @ResponseBody
//    @PostMapping("/ranking")
//    public ResponseEntity<RankingResponseDto> rankingFinpoint(@RequestParam(defaultValue = "0") int page,
//                                                              HttpServletRequest request) {
//        Integer myRanking = rankingService.getMyFinpointRankingByEmail(request);
//        Page<Member> memberPage = rankingService.getMemberSortByFinpoint(page);
//        RankingResponseDto rankingResponseDto = new RankingResponseDto(myRanking, memberPage);
//        return ResponseEntity.ok(rankingResponseDto);
//    }

}
