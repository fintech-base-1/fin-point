package com.fp.finpoint.domain.member.oauth.kakaooauth.kakao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto {

    public Long id;
    public kakao_account kakao_account;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class kakao_account {
        public boolean email_needs_agreement;
        public boolean is_email_valid;
        public boolean is_email_verified;
        public String email;
    }


}
