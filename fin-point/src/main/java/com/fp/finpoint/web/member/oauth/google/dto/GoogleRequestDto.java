package com.fp.finpoint.web.member.oauth.google.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleRequestDto {
    private String clientId;
    private String redirectUri;
    private String clientSecret;
    private String responseType;
    private String scope;
    private String code;
    private String accessType;
    private String grantType;
    private String state;
    private String includeGrantedScopes;
    private String loginHint;
    private String prompt;
}
