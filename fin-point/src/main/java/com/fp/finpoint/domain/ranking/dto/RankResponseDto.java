package com.fp.finpoint.domain.ranking.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankResponseDto {
    private String email;
    private int typeCount;
    private Long pieceRetainCount; // double?
    private Long assetAmount;
    private String image;
    private String nickname;
}
