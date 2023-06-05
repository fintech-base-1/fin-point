package com.fp.finpoint.domain.piece.service;

import com.fp.finpoint.domain.piece.Entity.Piece;
import com.fp.finpoint.domain.piece.repository.PieceRepository;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import com.fp.finpoint.web.piece.dto.PieceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PieceService {

    private final PieceRepository pieceRepository;

    public void create(PieceDto pieceDto) {
        pieceRepository.save(pieceDto.toEntity());
    }

    public void update(Long pieceId, Long count) {
        Piece savedPiece = pieceRepository.findById(pieceId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.PIECE_NOT_FOUND));
        if (savedPiece.getCount() < count) {
            throw new BusinessLogicException(ExceptionCode.PIECE_NOT_ENOUGH);
        }
        Long price = savedPiece.getPrice() * count;
        savedPiece.updateCount(count);
        //todo:member가 가진 finpoint가 count * price 보다 적다면 exception
    }
}
