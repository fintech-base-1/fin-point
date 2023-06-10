package com.fp.finpoint.domain.invest.service;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.entity.InvestDto;
import com.fp.finpoint.domain.invest.repository.InvestRepository;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.domain.piece.Entity.Piece;
import com.fp.finpoint.domain.piece.Entity.PieceMember;
import com.fp.finpoint.domain.piece.repository.PieceMemberRepository;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import com.fp.finpoint.global.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvestService {

    private final InvestRepository investRepository;
    private final MemberRepository memberRepository;
    private final PieceMemberRepository pieceMemberRepository;

    //게시글 리스트.
    public List<Invest> getInvestList() {

        return this.investRepository.findAll();
    }

    // 특정 게시글.
    public Invest investDetail(Long id) {
        return investRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.INVEST_NOT_FOUND));
    }

    //게시글 생성.
    @Transactional
    public void create(InvestDto investDto, String email) {
        Member findMember = memberRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
//        investDto.setMember(findMember);
//        investRepository.save(investDto.toEntity());
        Invest invest = investDto.toEntity();
        invest.setMember(findMember);
        Piece piece = new Piece(investDto.getPieceName(), investDto.getPiecePrice(), investDto.getPieceCount());
        invest.setPiece(piece);
        investRepository.save(invest);
    }

    //게시글 삭제.
    public void deleteInvest(Long id) {
        investRepository.deleteById(id);
    }

    //게시글 수정
    public void updateInvest(InvestDto investDto){

        investRepository.save(investDto.toEntity());
    }


    //리스트 페이징
    public Page<Invest> investList(Pageable pageable){
        return investRepository.findAll(pageable);
    }


    //검색 페이징 제목만
    public Page<Invest> investSearchList(String SearchKeyword,Pageable pageable){
        return investRepository.findBySubjectContaining(SearchKeyword,pageable);

    }

    @Transactional
    public void purchase(Long id, HttpServletRequest request, Long count) {
        String email = CookieUtil.getEmailToCookie(request);
        Member savedMember = memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        Invest invest = investRepository.findById(id).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.INVEST_NOT_FOUND));
        Piece savedPiece = invest.getPiece();
        if (savedPiece.getCount() < count) {
            throw new BusinessLogicException(ExceptionCode.PIECE_NOT_ENOUGH);
        }
        savedPiece.updateCount(count);
        Piece newPiece = new Piece(savedPiece.getName(), savedPiece.getPrice(), count);
        mapPieceAndMember(newPiece, savedMember);
    }

    @Transactional
    public void mapPieceAndMember(Piece piece, Member member) {
        PieceMember pieceMember = new PieceMember(member, piece);
        pieceMember.setMember(member);
        pieceMember.setPiece(piece);
        pieceMemberRepository.save(pieceMember);
    }
}
