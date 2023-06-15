package com.fp.finpoint.domain.openbank.repository;

import com.fp.finpoint.domain.openbank.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByUserSeqNo(String seq);
}
