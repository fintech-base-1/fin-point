package com.fp.finpoint.global.util;

import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Component
@RequiredArgsConstructor
public class CookieUtil {

    public static void setCookieInHeader(HttpServletResponse response, String accessToken) {

        ResponseCookie cookie = ResponseCookie.from("Authorization", accessToken)
//                .maxAge(3 * 24 * 60 * 60) // 쿠키 유효기간 설정 (3일)
                .path("/")
                .secure(true)
//                .httpOnly(true)
                .sameSite("None")
                .build();

        response.setHeader(SET_COOKIE, String.valueOf(cookie));
    }

    public static void setCookie(HttpServletResponse response, String accessToken) {
        String encodedValue = URLEncoder.encode(accessToken, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION, encodedValue);
//        cookie.setMaxAge(3 * 24 * 60 * 60); // 유효기간 max: 3일
//        cookie.setHttpOnly(true); // XSS 공격 방지
//        cookie.setSecure(true); // HTTPS 적용 시
        response.addCookie(cookie);
    }

    public static String getEmailToCookie(HttpServletRequest request) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                String name = c.getName();
                if (name.equals("Authorization")) {
                    return JwtUtil.getEmail(c.getValue());
                }
            }
        }
        throw new BusinessLogicException(ExceptionCode.TOKEN_NOT_FOUND);
    }

    public void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization")) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
}
