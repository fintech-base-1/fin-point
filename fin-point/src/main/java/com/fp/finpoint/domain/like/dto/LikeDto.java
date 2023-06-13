package com.fp.finpoint.domain.like.dto;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {

    public Long id;
    public Member member;
    private Invest invest;

//    public Like toEntity(){
//
//        return new Like(this.id, this.member, this.invest);
//    }
}
