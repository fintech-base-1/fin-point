package com.fp.finpoint.domain.invest.repository;

import com.fp.finpoint.domain.invest.entity.Invest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestRepository extends JpaRepository<Invest, Long> {


    //페이징, 검색
    Page<Invest> findBySubjectContaining(String searchKeyword, Pageable pageable);
}
