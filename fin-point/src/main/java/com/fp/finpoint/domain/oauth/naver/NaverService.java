package com.fp.finpoint.domain.oauth.naver;

import com.fp.finpoint.domain.member.service.MemberService;
import com.fp.finpoint.domain.oauth.OauthClient;
import com.fp.finpoint.domain.oauth.feign.NaverGetProfileFeign;
import com.fp.finpoint.domain.oauth.feign.NaverLoginFeign;
import com.fp.finpoint.global.util.JwtUtil;
import com.fp.finpoint.web.oauth.dto.naver.NaverProfileResponseDto;
import com.fp.finpoint.web.oauth.dto.naver.NaverResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NaverService {

    private final NaverLoginFeign naverLoginFeign;
    private final NaverGetProfileFeign naverGetProfileFeign;
    private final MemberService memberService;

    @Value("${oauth.naver.client_id}")
    private String client_id;
    @Value("${oauth.naver.client_secret}")
    private String client_secret;
    @Value("${oauth.naver.callback}")
    private String redirect_uri;

    public String getRequireUrl() {
        String reqUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" + client_id
                + "&redirect_uri=" + redirect_uri + "&state=1546578234123";
        return reqUrl;
    }

    public String loginService(String code, String state) {
        NaverResponseDto naverResponseDto =
                naverLoginFeign.login("authorization_code", client_id, client_secret, code, state);
        String accessToken = "Bearer " + naverResponseDto.getAccess_token();
        log.info("accessToken = {}" , accessToken);
        NaverProfileResponseDto naverProfileResponseDto = naverGetProfileFeign.getProfile(accessToken);
        String email = naverProfileResponseDto.getResponse().getEmail();
        log.info("email = {}", naverProfileResponseDto.getResponse().getEmail());
        log.info("nickname = {}", naverProfileResponseDto.getResponse().getNickname());
        memberService.manageDuplicateOAuthLogin(email, OauthClient.NAVER);
        return JwtUtil.createAccessToken(email);
    }

}
