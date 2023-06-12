package com.fp.finpoint.web.ranking.controller;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.domain.ranking.dto.RankRequestDto;
import com.fp.finpoint.domain.ranking.dto.RankResponseDto;
import com.fp.finpoint.domain.ranking.service.RankingService;
import com.fp.finpoint.web.ranking.RankingDto.RankingResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
//@RequestMapping("/finpoint")
public class RankingController {

    private final RankingService rankingService;
    private final MemberRepository memberRepository;

    @GetMapping("/ranking")
    public String ranking() {
        return "ranking";
    }

//    @ResponseBody
//    @GetMapping("/ranking/data")
//    public Page<MemberDto> ranking(@RequestParam(defaultValue = "0") int page) {
//        return rankingService.getMemberRankingByAllPiecesPriceTest(page);
//    }
    @ResponseBody
    @PostMapping("/rank")
    public List<RankResponseDto> getRankList(@RequestParam(defaultValue = "type") String standard,
                                             @RequestParam(defaultValue = "1", required = false) int page,
                                             @RequestParam(defaultValue = "5", required = false) int size) {
        return rankingService.getRankList(standard, page, size);
    }

    @ResponseBody
    @PostMapping("/ranking/data")
    public List<RankResponseDto> getRankListTest(@RequestBody RankRequestDto rankRequestDto) {
        return rankingService.getRankListTest(rankRequestDto.getStandard(), rankRequestDto.getPage(), 5);
    }

}
