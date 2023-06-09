package com.fp.finpoint.domain.invest.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private Long imgSize;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private LocalDateTime deadline;

    private Integer read_count;

    private Integer like_count;

    private String category;

    private Long seller_id;//작성자

    public Invest(String subject, String content, String imgUrl) {
        this.subject = subject;
        this.content = content;
        this.imgUrl = imgUrl;
    }
}
