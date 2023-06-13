package com.fp.finpoint.domain.invest.entity;

import com.fp.finpoint.domain.file.entity.FileEntity;
import com.fp.finpoint.domain.like.entity.Like;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.piece.Entity.Piece;
import com.fp.finpoint.global.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Invest extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;//제목

    @Column(columnDefinition = "TEXT")
    private String content;//내용

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fileEntity_id")
    private FileEntity fileEntity;

    private LocalDateTime deadline;

    private Integer read_count;//조회수

    private String category;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member; //회원

    @OneToMany(mappedBy = "invest")
    private List<Like> likes = new ArrayList<>();//좋아요

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "piece_id")
    private Piece piece;

    public Invest(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    @Column(columnDefinition = "integer default 0")
    private Integer likeCnt;

    public Invest(String subject, String content, Long id, Member member) {
        this.subject = subject;
        this.content = content;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getInvests().add(this);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

//    public void setFileEntity(FileEntity fileEntity) {
//        this.fileEntity = fileEntity;
//    }
}
