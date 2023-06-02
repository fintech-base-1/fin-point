package com.fp.finpoint.web.oauth.kakao.model;

import lombok.Data;

@Data
public class KakaoProfile {
    public Long id;
    public String connected_at;
    public KakaoAccount kakao_account;

    @Data
    class KakaoAccount {
        public Boolean profile_nickname_needs_agreement;
        public Boolean profile_image_needs_agreement;
        public Boolean has_email;
        public Boolean email_needs_agreement;

    }

}


