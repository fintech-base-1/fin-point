package com.fp.finpoint.web.ranking;

import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.domain.ranking.RankingDto.RankingResponseDto;
import com.fp.finpoint.domain.ranking.dto.RankResponseDto;
import com.fp.finpoint.domain.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/finpoint")
public class RankingController {

    private final RankingService rankingService;
    private final MemberRepository memberRepository;

    @GetMapping("/ranking")
    public String ranking() {
        return "ranking";
    }


    @ResponseBody
    @PostMapping("/rank")
    public List<RankResponseDto> getRankList(@RequestParam(defaultValue = "type") String standard,
                                             @RequestParam(defaultValue = "1", required = false) int page,
                                             @RequestParam(defaultValue = "5", required = false) int size) {
        return rankingService.getRankList(standard, page, size);
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

//    @ResponseBody
//    @GetMapping
//    public ResponseEntity<?> getRank(@RequestParam(defaultValue = "1", required = false) int page,
//                                     @RequestParam(defaultValue = "5", req)) {
//        // 모든 멤버 가져오기
//        int size = 5;
//        PageRequest.of(page, )
//        memberRepository.findAll(Pageable );
//
//
//    }


    @GetMapping("/ranking-asset")
    public String rankingAsset() {
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
