package com.fp.finpoint.domain.member.oauth.kakaooauth.feign;

import com.fp.finpoint.domain.member.oauth.kakaooauth.kakao.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao", url = "https://kauth.kakao.com/oauth/") // 카카오 개발자 사이트 고정값 uri
public interface LoginFeign {

    @GetMapping(value = "token", consumes = "application/x-www-form-urlencoded;charset=utf-8", produces = "application/json")
    ResponseDto login(@RequestParam("grant_type") String grant_type,
                      @RequestParam("client_id") String client_id,
                      @RequestParam("redirect_uri") String redirect_uri,
                      @RequestParam("code") String code);

}
