package com.fp.finpoint.domain.ranking.repository;


import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.piece.Entity.Piece;

import java.util.List;

public interface PieceCustomRepository {
    List<Piece> findPieceListByMember(Member member);
}
