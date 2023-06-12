package com.fp.finpoint.domain.ranking.service;

import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.domain.piece.Entity.Piece;
import com.fp.finpoint.domain.ranking.dto.RankResponseDto;
import com.fp.finpoint.domain.ranking.repository.PieceCustomRepositoryImpl;
import com.fp.finpoint.global.util.CookieUtil;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RankingService {

    private final MemberRepository memberRepository;
    private final PieceCustomRepositoryImpl pieceCustomRepository;

    public List<RankResponseDto> getRankList(String standard, int page, int size) {
        List<Member> members = memberRepository.findAll();
        List<RankResponseDto> rankResponseDtos = allMemberToRankResponseDto(members);
        sortRankByStandard(standard, rankResponseDtos);
        return processPaging(page, size, rankResponseDtos);
    }

    private List<RankResponseDto> allMemberToRankResponseDto(List<Member> members) {
        List<RankResponseDto> rankResponseDtos = new ArrayList<>();

        for (Member member : members) {
            List<Piece> pieceListByMember = pieceCustomRepository.findPieceListByMember(member);
            int typeCount = pieceListByMember.size();
            Long pieceRetainCount = 0L;
            Long assetAmount = 0L;
            for (Piece piece : pieceListByMember) {
                pieceRetainCount += piece.getCount();
                assetAmount += piece.getPrice();
            }

            RankResponseDto rankResponseDto = RankResponseDto.builder()
                    .email(member.getEmail())
                    .typeCount(typeCount)
                    .pieceRetainCount(pieceRetainCount)
                    .assetAmount(assetAmount)
                    .build();

            rankResponseDtos.add(rankResponseDto);
        }
        return rankResponseDtos;
    }

    private static void sortRankByStandard(String standard, List<RankResponseDto> rankResponseDtos) {
        switch (standard) {
            case "type":
                rankResponseDtos.sort((o1, o2) ->
                        o2.getTypeCount() - o1.getTypeCount());
            case "piece":
                rankResponseDtos.sort(((o1, o2) ->
                        Math.toIntExact(o2.getPieceRetainCount() - o1.getPieceRetainCount())));
            case "asset":
                rankResponseDtos.sort(((o1, o2) ->
                        Math.toIntExact(o2.getAssetAmount() - o1.getAssetAmount())));
        }
    }


    private static List<RankResponseDto> processPaging(int page, int size, List<RankResponseDto> rankResponseDtos) {
        int fromIndex = (page - 1) * size;
        if (rankResponseDtos.size() < fromIndex) {
            return Collections.emptyList();
        }

        int toIndex = Math.min(fromIndex + size, rankResponseDtos.size());

        return rankResponseDtos.subList(fromIndex, toIndex);
    }


    public Page<Member> getMemberSortByFinpoint(int pageNumber) {
        Sort sortByFinpoint = Sort.by("finpoint").descending();
        PageRequest pageable = PageRequest.of(pageNumber, 5, sortByFinpoint);
        return memberRepository.findAll(pageable);
    }

    public int getMyFinpointRankingByEmail(HttpServletRequest request) {
        List<Member> allMembersSortedByFinpoint = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "finpoint"));
        String email = CookieUtil.getEmailToCookie(request);
        Optional<Member> member = memberRepository.findByEmail(email);
        return allMembersSortedByFinpoint.indexOf(member.get()) + 1;
    }

    public Page<Member> getMemberSortByAssets(int pageNumber) {
        Sort sortByAssets = Sort.by("assets").descending();
        PageRequest pageable = PageRequest.of(pageNumber, 5, sortByAssets);
        return memberRepository.findAll(pageable);
    }

    public int getMyAssetRankingByEmail(HttpServletRequest request) {
        List<Member> allMembersSortedByAssets = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "assets"));
        String email = CookieUtil.getEmailToCookie(request);
        Optional<Member> member = memberRepository.findByEmail(email);
        return allMembersSortedByAssets.indexOf(member.get()) + 1;
    }

}
