package com.fp.finpoint.domain.ranking.dto;

import lombok.Data;

@Data
public class RankRequestDto {
    private String standard;
    private int page;
    private int size;
}
