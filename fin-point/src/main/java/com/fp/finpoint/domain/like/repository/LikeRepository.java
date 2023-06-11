package com.fp.finpoint.domain.like.repository;

import com.fp.finpoint.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

        //void deleteByMemberAndInvest(Member member, Invest invest); //좋아요 삭제

        void deleteByInvest_IdAndMember_Email(Long invest_id, String email); //좋아요 취소


        //특정 게시물의 좋아요 확인
        boolean existsByInvest_IdAndMember_Email(Long invest_id, String email);


//        //특정 게시물의 좋아요 확인
//        boolean existsByInvest_IdAndMemberId(Long invest_id, Long memberId);//이메일 안되면?
}
