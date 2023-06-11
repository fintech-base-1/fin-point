package com.fp.finpoint.domain.piece.Entity;

import com.fp.finpoint.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PieceMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "piece_id")
    private Piece piece;

    public PieceMember(Member member, Piece piece) {
        this.member = member;
        this.piece = piece;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getPieceMembers().add(this);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        piece.getPieceMembers().add(this);
    }
}
