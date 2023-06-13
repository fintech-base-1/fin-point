package com.fp.finpoint.domain.like.entity;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String status;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "memberId")
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "invest_id")
    private Invest invest;


    public Like(Member member,Invest invest){
        this.member=member;
        this.invest=invest;

    }






}
