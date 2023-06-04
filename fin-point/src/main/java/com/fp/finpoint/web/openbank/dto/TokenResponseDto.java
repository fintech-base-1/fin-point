package com.fp.finpoint.web.openbank.dto;

import com.fp.finpoint.domain.openbank.Entity.Token;
import lombok.Data;

@Data
public class TokenResponseDto {

    private String access_token;
    private String token_type;
    private String expires_in;
    private String refresh_token;
    private String scope;
    private String user_seq_no;

    public Token toEntity() {
        return new Token(this.access_token, this.token_type, this.expires_in, this.refresh_token, this.scope, this.user_seq_no);
    }
}
