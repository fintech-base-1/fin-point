package com.fp.finpoint.domain.invest.dto;

import com.fp.finpoint.domain.invest.entity.Invest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestDto {
    private String subject;//제목
    private String content;//내용
//    public Long id;//글 번호
//    public Member member;

    // 조각정보
    private String pieceName;
    private Long piecePrice;
    private Long pieceCount;

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
