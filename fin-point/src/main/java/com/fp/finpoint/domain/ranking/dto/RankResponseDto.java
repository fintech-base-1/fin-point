package com.fp.finpoint.domain.ranking.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.UrlResource;

@Data
@NoArgsConstructor
public class RankResponseDto {
    private String email;
    private int typeCount;
    private Long pieceRetainCount; // double?
    private Long assetAmount;
    private String nickname;
    private Long userId;
}
