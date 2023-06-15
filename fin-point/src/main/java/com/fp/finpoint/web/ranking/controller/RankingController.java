package com.fp.finpoint.web.ranking.controller;
import com.fp.finpoint.domain.file.service.FileService;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.domain.ranking.dto.RankRequestDto;
import com.fp.finpoint.domain.ranking.dto.RankResponseDto;
import com.fp.finpoint.domain.ranking.service.RankingService;
import com.fp.finpoint.web.ranking.RankingDto.RankingResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/finpoint")
public class RankingController {

    private final RankingService rankingService;
    private final FileService fileService;

    @GetMapping("/ranking")
    public String ranking() {
        return "user/ranking/ranking";
    }

    @ResponseBody
    @PostMapping("/ranking/data")
    public List<RankResponseDto> getRankList(@RequestBody RankingResponseDto.Post post) throws MalformedURLException {
        return rankingService.getRankList(post.getStandard(), post.getPage(), 5);
    }


    @ResponseBody
    @GetMapping("/ranking/image/{id}")
    public Resource rankingImage(@PathVariable(value = "id") Long id) throws MalformedURLException {
        return fileService.getRankingImageUrl(id);
    }


}
