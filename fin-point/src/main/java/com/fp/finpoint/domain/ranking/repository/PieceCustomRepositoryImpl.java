package com.fp.finpoint.domain.ranking.repository;

import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.piece.Entity.Piece;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.fp.finpoint.domain.piece.Entity.QPiece.piece;
import static com.fp.finpoint.domain.piece.Entity.QPieceMember.pieceMember;

@Repository
public class PieceCustomRepositoryImpl implements PieceCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PieceCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Piece> findPieceListByMember(Member member) {
        List<Piece> pieces = jpaQueryFactory
                .selectFrom(piece)
                .join(piece.pieceMembers, pieceMember)
                .where(pieceMember.member.eq(member))
                .fetch();

        return pieces;
    }
}
