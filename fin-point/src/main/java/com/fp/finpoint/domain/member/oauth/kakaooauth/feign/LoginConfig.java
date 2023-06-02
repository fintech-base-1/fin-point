package com.fp.finpoint.domain.member.oauth.kakaooauth.feign;

import feign.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {
    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }
}