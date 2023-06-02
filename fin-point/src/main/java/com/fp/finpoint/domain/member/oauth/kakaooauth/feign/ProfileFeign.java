package com.fp.finpoint.domain.member.oauth.kakaooauth.feign;

import com.fp.finpoint.domain.member.oauth.kakaooauth.kakao.ProfileResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoProfile", url = "https://kapi.kakao.com/v2/user/")
public interface ProfileFeign {

    @GetMapping(value = "/me", consumes = "application/x-www-form-urlencoded;charset=utf-8", produces = "application/json")
    ProfileResponseDto getProfile(@RequestHeader(value = "Authorization") String accessToken,
                                  @RequestHeader(value = "Content-type") String contentType);
}
