package com.fp.finpoint.domain.oauth.feign;

import com.fp.finpoint.web.oauth.dto.naver.NaverResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver", url = "https://nid.naver.com/oauth2.0")
public interface NaverLoginFeign {

    @GetMapping(value = "/token", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    NaverResponseDto login(@RequestParam("grant_type") String grant_type,
                           @RequestParam("client_id") String client_id,
                           @RequestParam("client_secret") String client_secret,
                           @RequestParam("code") String code,
                           @RequestParam("state") String state);
}
