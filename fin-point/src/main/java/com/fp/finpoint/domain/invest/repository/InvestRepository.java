package com.fp.finpoint.domain.invest.repository;

import com.fp.finpoint.domain.invest.entity.Invest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InvestRepository extends JpaRepository<Invest, Long> {

    Page<Invest> findByTitleContaining(String searchKeyword, Pageable pageable);
}
