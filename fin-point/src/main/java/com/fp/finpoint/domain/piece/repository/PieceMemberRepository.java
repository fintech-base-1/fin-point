package com.fp.finpoint.domain.piece.repository;

import com.fp.finpoint.domain.piece.Entity.PieceMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PieceMemberRepository extends JpaRepository<PieceMember, Long> {
    List<PieceMember> findByMember_memberId(Long memberId);
}
