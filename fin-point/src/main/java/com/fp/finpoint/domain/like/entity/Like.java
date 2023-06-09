package com.fp.finpoint.domain.like.entity;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Builder
@Getter
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "memberId")
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "invest_id")
    private Invest invest;

    public Like(Long id,Member member,Invest invest){
        this.id=id;
        this.member=member;
        this.invest=invest;

    }






}
