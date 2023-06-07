package com.fp.finpoint.domain.invest.entity;

import com.fp.finpoint.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private Integer read_count;

    private Integer like_count;

    private String category;

    private Long seller_id;//작성자

    @ManyToMany
    Set<Member> liked;// 좋아요

    public Invest(String subject, String content, Long id, Set<Member> liked) {
        this.subject = subject;
        this.content = content;
        this.id = id;
        this.liked=liked;
    }

}
