package com.fp.finpoint.domain.like;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponse {

    private Long id;
    private Member member;
    private Invest invest;


}
