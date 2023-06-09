package com.fp.finpoint.config;

import com.fp.finpoint.global.interceptor.CookieInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CookieInterceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //todo: 모든 페이지를 열어버리고 마이페이지, 투자하기에서의 글 상세페이지, 로그아웃 페이지를 막는것이 효율적이지 않을까?
        registry.addInterceptor(interceptor)
                .addPathPatterns("/finpoint/refresh");

//                .excludePathPatterns("/join", "/finpoint/join", "/login", "/finpoint/login", "/finpoint/mailconfirm", "/requesttoken", "/", "/finpoint/oauth/*");
//                        "/finpoint/**", "/**");\
    }
}
