package com.fp.finpoint.domain.invest.repository;

import com.fp.finpoint.domain.invest.entity.Invest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestRepository extends JpaRepository<Invest, Long> {

    Page<Invest> findBySubjectContaining(String searchKeyword, Pageable pageable);
}
