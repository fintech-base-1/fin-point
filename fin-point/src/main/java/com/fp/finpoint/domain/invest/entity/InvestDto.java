package com.fp.finpoint.domain.invest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fp.finpoint.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "invest")
public class InvestDto extends Invest {

//    private Long id;
//
//    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
//    @JoinColumn(name = "member_id", updatable = false)
//    @JsonBackReference
//    private Member member;
//
//    @Column(nullable = false)
//    private String subject;
//
//    @Column(columnDefinition = "TEXT" , nullable = false)
//    private String content;
//
//    @OneToMany(
//            mappedBy = "board",
//            cascade = {CascadeType.PERSIST , CascadeType.REMOVE},
//            orphanRemoval = true
//    )
//    private List<PhotoDto> photoDtoList = new ArrayList<>();
//
//    @Builder
//    public InvestDto(Member member, String title, String content) {
//        this.member = member;
//        this.subject = subject;
//        this.content = content;
//    }
//
//    public void update(String subject, String content) {
//        this.subject = subject;
//        this.content = content;
//    }
//
//    public void addPhoto(PhotoDto photoDto) {
//        this.photoDtoList.add(photoDto);
//
//        if(photoDto.getInvest() != this)
//
//            photoDto.setInvest(this);
//    }



    public String subject;//제목
    public String content;//내용
    public String imgUrl;


    // 여기서 Invest는 return 타입
    public Invest toEntity() {

        return new Invest(this.subject, this.content, this.imgUrl);
    }
}
