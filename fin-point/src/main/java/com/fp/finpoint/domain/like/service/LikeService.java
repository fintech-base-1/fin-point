package com.fp.finpoint.domain.like.service;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.repository.InvestRepository;
import com.fp.finpoint.domain.like.entity.Like;
import com.fp.finpoint.domain.like.repository.LikeRepository;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final InvestRepository investRepository;


    //특정 게시물 유저 좋아요 확인
    public boolean findLike(Long invest_id, String email) {
        memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return likeRepository.existsByInvest_IdAndMember_Email(invest_id, email);

    }

    //좋아요 저장
    @Transactional
    public boolean saveLike(Long invest_id, String email){

        //로그인한 유저가 해당 게시물을 좋아요 했는지 안 했는지 확인
        if(!findLike(invest_id,email)) {

            //좋아요 하지 않은 게시물이면 좋아요 추가, true 반환
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
            Invest invest = investRepository.findById(invest_id)
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INVEST_NOT_FOUND));

            //좋아요 엔티티 생성
            Like like = new Like(member, invest);
            likeRepository.save(like);
            investRepository.okLike(invest_id);

            return true;
        }else{
            //좋아요 한 게시물이면 좋아요 삭제, false 반환
            likeRepository.deleteByInvest_IdAndMember_Email(invest_id,email);
            investRepository.noLike(invest_id);

            return false;


        }

    }
}


