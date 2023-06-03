package com.fp.finpoint.web.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoResponseDto {

    private String token_type;
    private String access_token;
    private String expires_in;
}