package com.fp.finpoint.domain.invest.repository;

import com.fp.finpoint.domain.invest.entity.Invest;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface InvestRepository extends JpaRepository<Invest, Long> {


    //페이징, 검색
    Page<Invest> findBySubjectContaining(String searchKeyword, Pageable pageable);

    //좋아요 추가
    @Modifying
    @Query(value = "update Invest invest set invest.likeCnt = invest.likeCnt + 1 where invest.id = :invest_id")
    void okLike(@Param("invest_id") Long invest_id);

    //좋아요 삭제
    @Modifying
    @Query(value = "update Invest invest set invest.likeCnt = invest.likeCnt - 1 where invest.id = :invest_id")
    void noLike(@Param("invest_id") Long invest_id);


}
