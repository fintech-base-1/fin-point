package com.fp.finpoint.domain.oauth.feign;


import com.fp.finpoint.web.oauth.dto.NaverProfileResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "naverProfile", url = "https://openapi.naver.com/v1/nid")
public interface NaverGetProfileFeign {

    @GetMapping(value = "/me", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    NaverProfileResponseDto getProfile(@RequestHeader(value = "Authorization") String accessToken);
}
