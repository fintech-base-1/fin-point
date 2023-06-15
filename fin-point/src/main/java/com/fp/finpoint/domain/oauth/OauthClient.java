package com.fp.finpoint.domain.oauth;

public enum OauthClient {
    NOTHING("일반유저"),
    GOOGLE("구글"),
    KAKAO("카카오"),
    NAVER("네이버");

    private final String description;

    OauthClient(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
