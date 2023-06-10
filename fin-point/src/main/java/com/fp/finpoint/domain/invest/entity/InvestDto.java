package com.fp.finpoint.domain.invest.entity;

import com.fp.finpoint.domain.member.entity.Member;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestDto {

    public String subject;//제목
    public String content;//내용
    public Long id;//글 번호
    public Member member;

//    Set<Member> liked;// 좋아요
//    private Integer liked_cnt;// 좋아요 수

    // 여기서 Invest는 return 타입
    public Invest toEntity() {

        return new Invest(this.subject, this.content, this.id, this.member);
    }
}
