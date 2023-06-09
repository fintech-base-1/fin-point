package com.fp.finpoint.web.mypage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MypageDto {

    public Long finpoint;

    public Long pieceKind;

    public Long pieceCnt;

    public Long piecePrice;

}
