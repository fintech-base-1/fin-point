package com.fp.finpoint.web.ranking.controller;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.ranking.service.RankingService;
import com.fp.finpoint.web.ranking.RankingDto.RankingResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
        Integer myRanking = Integer.valueOf("7");
        List<MemberDto> members = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String email = "test" + (i + 1) + "@test.com";
            String nickname = "테스트" + (i + 1);
            MemberDto memberDto = new MemberDto();
            memberDto.setEmail(email);
            memberDto.setNickname(nickname);
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
        model.addAttribute("memberPage", memberPage);
        model.addAttribute("myRanking", myRanking);
        return "ranking";
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
