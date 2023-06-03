package com.fp.finpoint.domain.oauth.feign;

import com.fp.finpoint.web.oauth.dto.KakaoProfileResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoProfile", url = "https://kapi.kakao.com/v2/user/")
public interface KakaoGetProfileFeign {

    @GetMapping(value = "/me", consumes = "application/x-www-form-urlencoded;charset=utf-8", produces = "application/json")
    KakaoProfileResponseDto getProfile(@RequestHeader(value = "Authorization") String accessToken,
                                       @RequestHeader(value = "Content-type") String contentType);
}
