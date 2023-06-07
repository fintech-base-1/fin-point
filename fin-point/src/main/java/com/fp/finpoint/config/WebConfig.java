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
        registry.addInterceptor(interceptor)
                .excludePathPatterns("/finpoint/join", "/finpoint/login", "/finpoint/mail-confirm", "/requesttoken", "/", "/finpoint/oauth/*",
                        "/finpoint/**");
    }
}
