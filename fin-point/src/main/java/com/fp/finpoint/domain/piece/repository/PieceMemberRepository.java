package com.fp.finpoint.domain.piece.repository;

import com.fp.finpoint.domain.piece.Entity.PieceMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceMemberRepository extends JpaRepository<PieceMember, Long> {
}
