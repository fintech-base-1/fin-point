package com.fp.finpoint.web.piece.dto;

import com.fp.finpoint.domain.piece.Entity.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieceDto {

    public Long price;
    public Long count;

    public Piece toEntity() {
        return new Piece(this.price, this.count);
    }
}
