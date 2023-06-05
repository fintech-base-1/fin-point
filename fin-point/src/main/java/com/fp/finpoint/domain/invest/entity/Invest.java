package com.fp.finpoint.domain.invest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String imgUrl;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private LocalDateTime deadline;

    private Integer read_count;

    private Integer like_count;

    private String category;

    private Long seller_id;

    public Invest(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
