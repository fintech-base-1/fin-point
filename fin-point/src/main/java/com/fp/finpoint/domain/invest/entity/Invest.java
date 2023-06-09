package com.fp.finpoint.domain.invest.entity;

import com.fp.finpoint.domain.like.entity.Like;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Invest extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;//제목

    @Column(columnDefinition = "TEXT")
    private String content;//내용

    private String imgUrl;

    private LocalDateTime deadline;

    private Integer read;//조회수

    private String category;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member; //회원

    @OneToMany(mappedBy = "invest")
    private List<Like> likes = new ArrayList<>();//좋아요

    public Invest(String subject, String content, Long id, Member member) {
        this.subject = subject;
        this.content = content;
        this.id = id;
        this.member = member;
    }

}
