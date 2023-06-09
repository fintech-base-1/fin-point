package com.fp.finpoint.global.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.response.ErrorResponse;
import com.fp.finpoint.global.util.CookieUtil;
import com.fp.finpoint.global.util.JwtUtil;
import com.fp.finpoint.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.fp.finpoint.global.exception.ExceptionCode.TOKEN_EXPIRED;

@Slf4j
@Component
@RequiredArgsConstructor
public class CookieInterceptor implements HandlerInterceptor {

    private final MemberRepository memberRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String accessToken = CookieUtil.getAccessToken(cookies);
        if (accessToken == null || !accessToken.startsWith(JwtUtil.PREFIX)) {
            log.info("accessToken = {}", accessToken);
            errorResponse(response, "accessToken is not valid");
            return false;
        }

        // email 정보를 확인
        String email = JwtUtil.getEmail(accessToken);
        if (email == null || !memberRepository.existsByEmail(email)) {
            log.info("email = {}", email);
            errorResponse(response, "email from token is not exist");
            return false;
        }

        // Jwt 토큰 검증
        try {
            JwtUtil.validateAccessToken(accessToken);
        } catch (BusinessLogicException e) {
            log.info("accessToken expired message = {}", e.getExceptionCode().getMessage());
            if (e.getExceptionCode().equals(TOKEN_EXPIRED)) {
                String refreshToken = CookieUtil.getRefreshToken(cookies);
                try {
                    JwtUtil.validateRefreshToken(email, refreshToken);
                    String newAccessToken = JwtUtil.createAccessToken(email);
                    log.info("newAccessToken={}", newAccessToken);
                    CookieUtil.setAccessTokenInCookie(response, JwtUtil.AUTHORIZATION, newAccessToken);
                    return true;
                } catch (BusinessLogicException e1) {
                    CookieUtil.deleteCookie(response);
                    RedisUtil.deleteKey(email);
                    response.sendRedirect("/login");
                    log.info("refresh error message = {}", e1.getExceptionCode().getMessage());
                    return false;
                }
            }
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex != null) {
            log.info("exception occurred={}", ex.getMessage());
        }
    }

    private void errorResponse(HttpServletResponse response, String message) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String error = mapper.writeValueAsString(ErrorResponse.of(HttpStatus.valueOf(401), message));

        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(error);
    }
}

