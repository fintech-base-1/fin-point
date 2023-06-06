//package com.fp.finpoint.global.interceptor;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fp.finpoint.domain.member.repository.MemberRepository;
//import com.fp.finpoint.global.exception.response.ErrorResponse;
//import com.fp.finpoint.global.jwt.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class Interceptor implements HandlerInterceptor {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String header = request.getHeader(JwtUtil.AUTHORIZATION);
//        // Authorization 이라는 이름의 header 가 없거나 Bearer 로 시작하지 않는다면 return false
//        if (header == null || !header.startsWith(JwtUtil.PREFIX)) {
//            log.info("header = {}", header);
//            errorResponse(response, "header is not valid");
//            return false;
//        }
//        String email = JwtUtil.getEmail(header);
//        // email 정보를 확인
//        if (email == null || !memberRepository.existsByEmail(email)) {
//            log.info("email = {}", email);
//            errorResponse(response, "email from token is not exist");
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        if (ex != null) {
//            log.info("exception occurred={}", ex.getMessage());
//        }
//    }
//
//    private void errorResponse(HttpServletResponse response, String message) throws IOException {
//
//        ObjectMapper mapper = new ObjectMapper();
//        String error = mapper.writeValueAsString(ErrorResponse.of(HttpStatus.valueOf(401), message));
//
//        response.setStatus(401);
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(error);
//    }
//}
