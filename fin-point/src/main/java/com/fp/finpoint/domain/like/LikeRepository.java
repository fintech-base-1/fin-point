package com.fp.finpoint.domain.like;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LikeRepository extends JpaRepository<Like, Long> {
        void deleteByMemberAndInvest(Member member, Invest invest); //좋아요 삭제
}
