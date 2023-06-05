package com.fp.finpoint.domain.invest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Entity
@AllArgsConstructor//여기에 필드에 쓴 모든 생성자만 만들어줌
@NoArgsConstructor//기본 생성자를 만들어줌
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //게시물 번호

    private String title; //제목
    private String content; //내용
    private String name; //작성자
    //private String read; //조회수

//    @CreationTimestamp
//    @Column(updatable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;

    public Board(String title, String content, String name, Integer id){
        this.title=title;
        this.content=content;
        this.name=name;
        this.id=id;
    }

    public void setTitle(String title) {
    }
}
