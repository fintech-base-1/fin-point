package com.fp.finpoint.web.oauth.kakao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OAuthToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expire_in;
    private String scope;
    private int refresh_token_expires_in;
    private String id_token;
}