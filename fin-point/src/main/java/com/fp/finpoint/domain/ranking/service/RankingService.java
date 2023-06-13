package com.fp.finpoint.domain.ranking.service;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.domain.piece.Entity.Piece;
import com.fp.finpoint.domain.ranking.dto.RankResponseDto;
import com.fp.finpoint.domain.ranking.repository.PieceCustomRepositoryImpl;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class RankingService {

    private final MemberRepository memberRepository;
    private final PieceCustomRepositoryImpl pieceCustomRepositoryImpl;

    public List<RankResponseDto> getRankList(String standard, int page, int size) throws MalformedURLException {
        List<Member> members = memberRepository.findAll();
        List<RankResponseDto> rankResponseDtos = allMemberToRankResponseDto(members);
        sortRankByStandard(standard, rankResponseDtos);
        return processPaging(page, size, rankResponseDtos);
    }

    private List<RankResponseDto> allMemberToRankResponseDto(List<Member> members) throws MalformedURLException {
        List<RankResponseDto> rankResponseDtos = new ArrayList<>();

        for (Member member : members) {
            List<Piece> pieceListByMember = pieceCustomRepositoryImpl.findPieceListByMember(member);
            int typeCount = pieceListByMember.size();
            Long pieceRetainCount = 0L;
            Long assetAmount = 0L;

            for (Piece piece : pieceListByMember) {
                pieceRetainCount += piece.getCount();
                assetAmount += piece.getPrice() * piece.getCount();
            }

            UrlResource src = new UrlResource("file:" +member.getFileEntity().getSavedPath() );
            RankResponseDto rankResponseDto = new RankResponseDto();
            rankResponseDto.setEmail(member.getEmail());
            rankResponseDto.setTypeCount(typeCount);
            rankResponseDto.setPieceRetainCount(pieceRetainCount);
            rankResponseDto.setAssetAmount(assetAmount);
            if (rankResponseDto.getImage() != null) {
                rankResponseDto.setImage(src);
                System.out.println("이미지 들어있어요:) ");
            }

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

    public Page<MemberDto> getMemberRankingByAllPiecesPriceTest(int page) {
//        Sort sortByAllPiecesPrice = Sort.by("totalPrice").descending();
//        PageRequest pageable = PageRequest.of(page,5,sortByAllPiecesPrice);
        List<MemberDto> members = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            String email = "test" + (i + 1) + "@test.com";
            String nickname = "정렬확인랜덤" + random.nextInt(1000);
            Integer totalPrice = (1000000 - 10000 * i);
            MemberDto memberDto = new MemberDto();
            memberDto.setEmail(email);
            memberDto.setNickname(nickname);
            memberDto.setTotalPrice(totalPrice);
            memberDto.setImage("default.jpg");
            members.add(memberDto);
        }
        int pageSize = 5;
        int totalElements = members.size();
        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalElements);
        start = Math.min(start, totalElements);
        end = Math.min(end, totalElements);
        List<MemberDto> content = members.subList(start, end);
        Page<MemberDto> memberPage = new PageImpl<>(content, PageRequest.of(page, pageSize), totalElements);
        return memberPage;
    }


//    public List<RankResponseDto> getRankListTest(String standard, int page, int size) {
//        //        Sort sortByAllPiecesPrice = Sort.by("totalPrice").descending();
//        //        PageRequest pageable = PageRequest.of(page,5,sortByAllPiecesPrice);
//        List<RankResponseDto> members = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i < 50; i++) {
//            String email = "test" + (i + 1) + "@test.com";
//            String nickname = "nick" + random.nextInt(1000);
//            int typeCount = 50 - i;
//            Long pieceRetainCount = (long) (500 - 10 * i);
//            Long assetAmount = (long) (1000000 - 10000 * i);
//
//            RankResponseDto rankResponseDto = RankResponseDto.builder()
//                    .email(email)
//                    .nickname(nickname)
//                    .typeCount(typeCount) // ???
//                    .pieceRetainCount(pieceRetainCount)
//                    .assetAmount(assetAmount)
//                    .image("default.jpg")
//                    .build();
//            members.add(rankResponseDto);
//        }
//
//        sortRankByStandard(standard, members);
//        return processPaging(page, size, members);
//    }

}
