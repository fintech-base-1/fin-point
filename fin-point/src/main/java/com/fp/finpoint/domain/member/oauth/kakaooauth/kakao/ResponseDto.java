package com.fp.finpoint.domain.member.oauth.kakaooauth.kakao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private String token_type;
    private String access_token;
    private String expires_in;
}