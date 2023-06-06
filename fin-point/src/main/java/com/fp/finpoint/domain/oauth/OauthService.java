package com.fp.finpoint.domain.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class OauthService {

    public Cookie encodeCookie(String getToken){
        String encodedToken = URLEncoder.encode(getToken, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("Authorization", encodedToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

}
