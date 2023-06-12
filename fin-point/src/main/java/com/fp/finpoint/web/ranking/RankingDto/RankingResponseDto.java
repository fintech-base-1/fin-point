package com.fp.finpoint.web.ranking.RankingDto;

import com.fp.finpoint.domain.member.entity.Member;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class RankingResponseDto {

    private Integer myRanking;

    private Page<Member> memberPage;

    public RankingResponseDto(Integer myRanking, Page<Member> memberPage) {
        this.myRanking = myRanking;
        this.memberPage = memberPage;
    }
}
