package com.fp.finpoint.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Component
@RequiredArgsConstructor
public class CookieUtil {

    public static void setCookieInHeader(HttpServletResponse response, String accessToken) {

        ResponseCookie cookie = ResponseCookie.from("Authorization", accessToken)
//                .maxAge(3 * 24 * 60 * 60) // 쿠키 유효기간 설정 (3일)
                .path("/")
//                .secure(true)
                .httpOnly(true)
                .sameSite("None")
                .build();

        response.setHeader(SET_COOKIE, String.valueOf(cookie));
    }

    // 수정 핊요
    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies(); // 모든 쿠키 가져오기
        if (cookies != null) {
            for (Cookie c : cookies) {
                String name = c.getName(); // 쿠키 이름 가져오기
                String value = c.getValue(); // 쿠키 값 가져오기
                if (name.equals("refreshToken")) {
                    return value;
                }
            }
        }
        return null;
    }

    // 수정 핊요
    public void deleteCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", null)
                .maxAge(0) // 쿠키 유효기간 설정 (3일)
                .build();

        response.setHeader(SET_COOKIE, String.valueOf(cookie));
    }
}
