package com.fp.finpoint.global.util;

import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;

import static com.fp.finpoint.global.util.JwtUtil.AUTHORIZATION;
import static com.fp.finpoint.global.util.JwtUtil.REFRESH;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Slf4j
@Component
@RequiredArgsConstructor
public class CookieUtil {
    public static void setAccessTokenInCookie(HttpServletResponse response, String name, String accessToken) {
        ResponseCookie cookie = ResponseCookie.from(name, accessToken)
                .path("/")
                .httpOnly(true)
                .build();
        
        response.setHeader(SET_COOKIE, cookie.toString());
    }

    public static void addRefreshInCookie(HttpServletResponse response, String name, String accessToken) {
        ResponseCookie cookie = ResponseCookie.from(name, accessToken)
                .path("/")
                .httpOnly(true)
                .build();
        
        response.addHeader(SET_COOKIE, cookie.toString());
    }

    public static String getAccessToken(Cookie[] cookies) {
        return getCookieValue(AUTHORIZATION, cookies);
    }

    public static String getRefreshToken(Cookie[] cookies) {
        return getCookieValue(REFRESH, cookies);
    }

    private static String getCookieValue(String name, Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TOKEN_NOT_FOUND));
    }

    public static void deleteCookie(String name, HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(name, null)
                .maxAge(0)
                .path("/")
                .httpOnly(true)
                .build();

        response.setHeader(SET_COOKIE, cookie.toString());
    }

    public static String getEmailToCookie(HttpServletRequest request) {
        String accessToken = getCookieValue(JwtUtil.AUTHORIZATION, request.getCookies());
        String email = JwtUtil.getEmail(accessToken);
        return email;
    }
}