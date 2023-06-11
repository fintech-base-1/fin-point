package com.fp.finpoint.domain.invest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestDto {

    public String subject;//제목
    public String content;//내용
//    public Long id;//글 번호
//    public Member member;
    // 조각정보
    public String pieceName;
    public Long piecePrice;
    public Long pieceCount;

//    Set<Member> liked;// 좋아요
//    private Integer liked_cnt;// 좋아요 수

    // 여기서 Invest는 return 타입
//    public Invest toEntity() {
//
//        return new Invest(this.subject, this.content, this.id);
//    }
    public Invest toEntity() {
        return new Invest(this.subject, this.content);
    }
}
