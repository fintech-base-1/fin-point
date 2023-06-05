package com.fp.finpoint.domain.piece.repository;

import com.fp.finpoint.domain.piece.Entity.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long> {
}
