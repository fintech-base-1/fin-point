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

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final InvestRepository investRepository;

    public String goodInvest(Long id, String email){
        Member member= memberRepository.findByEmail(email).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        Invest invest= investRepository.findById(id).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));


//        LikeResponse goodResponse= new LikeReponse();
        if(member.getLikes().stream().anyMatch(like -> like.getInvest().equals(invest))) {
            likeRepository.deleteByMemberAndInvest(member, invest);
//            goodResponse.setmessage("좋아요 취소");
        }else {
            likeRepository.save(Like.builder().invest(invest).member(member).build());
//            goodResponse.setmessage("좋아요 성공");
        }
        return "success";
        }
    }


