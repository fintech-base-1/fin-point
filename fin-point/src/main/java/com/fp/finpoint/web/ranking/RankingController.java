package com.fp.finpoint.web.ranking;

import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.ranking.RankingDto.RankingResponseDto;
import com.fp.finpoint.domain.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/ranking")
    public String ranking(){
        return "ranking";
    }

    @ResponseBody
    @PostMapping("/ranking")
    public ResponseEntity<RankingResponseDto> ranking(@RequestParam(defaultValue = "0") int page,
                                              HttpServletRequest request) {
        Integer myRanking = rankingService.getMyFinpointRankingByEmail(request);
        Page<Member> memberPage = rankingService.getMemberSortByFinpoint(page);
        RankingResponseDto rankingResponseDto = new RankingResponseDto(myRanking, memberPage);
        return ResponseEntity.ok(rankingResponseDto);
    }

    @GetMapping("/ranking-asset")
    public String rankingAsset(){
        return "ranking";
    }

    @ResponseBody
    @PostMapping("/ranking-asset")
    public ResponseEntity<RankingResponseDto> rankingAsset(@RequestParam(defaultValue = "0") int page,
                                                      HttpServletRequest request) {
        Integer myRanking = rankingService.getMyAssetRankingByEmail(request);
        Page<Member> memberPage = rankingService.getMemberSortByAssets(page);
        RankingResponseDto rankingResponseDto = new RankingResponseDto(myRanking, memberPage);
        return ResponseEntity.ok(rankingResponseDto);
    }

}
