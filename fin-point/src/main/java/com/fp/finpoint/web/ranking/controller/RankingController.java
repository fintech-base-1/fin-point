package com.fp.finpoint.web.ranking.controller;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/ranking")
    public String rankingView() {
        return "ranking";
    }
    @ResponseBody
    @GetMapping("/ranking/data")
    public Page<MemberDto> ranking(@RequestParam(defaultValue = "0") int page) {
        return rankingService.getMemberRankingByAllPiecesPriceTest(page);
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
