package com.fp.finpoint.config;

import feign.Client;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.fp.finpoint")
public class FeignConfig {
    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }
}
