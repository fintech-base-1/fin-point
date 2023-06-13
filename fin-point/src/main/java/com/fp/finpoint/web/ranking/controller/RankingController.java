package com.fp.finpoint.web.ranking.controller;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.domain.ranking.dto.RankResponseDto;
import com.fp.finpoint.domain.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.net.MalformedURLException;
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
        return "user/ranking/ranking";
    }

    @ResponseBody
    @PostMapping("/ranking/data")
    public List<RankResponseDto> getRankList(@RequestParam(defaultValue = "type") String standard,
                                             @RequestParam(defaultValue = "1", required = false) int page,
                                             @RequestParam(defaultValue = "5", required = false) int size) throws MalformedURLException {
        return rankingService.getRankList(standard, page, 5);
    }

}
