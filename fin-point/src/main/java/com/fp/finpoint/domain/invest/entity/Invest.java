package com.fp.finpoint.domain.invest.entity;

//import com.fp.finpoint.domain.like.Like;
import com.fp.finpoint.domain.like.Like;
import com.fp.finpoint.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Invest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;//제목

    @Column(columnDefinition = "TEXT")
    private String content;//내용

    private String imgUrl;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private LocalDateTime deadline;

    private Integer read_count;//조화수

    private Integer liked_cnt;//좋아요 수

    private String category;


//    @ManyToMany
//    Set<Member> liked;// 좋아요

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member; //회원

    private Long seller_id;//작성자

    @OneToMany(mappedBy = "invest")
    private List<Like> likes = new ArrayList<>();//좋아요

    public Invest(String subject, String content, Long id, Member member) {
        this.subject = subject;
        this.content = content;
        this.id = id;
        this.member = member;
    }


}
