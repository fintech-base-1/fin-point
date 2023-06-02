package com.fp.finpoint.domain.member.oauth.naver;

import com.fp.finpoint.domain.member.oauth.naver.feign.LoginFeign;
import com.fp.finpoint.domain.member.oauth.naver.feign.ProfileFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
@Slf4j
public class NaverService {

    @Autowired
    private LoginFeign loginFeign;
    @Autowired
    private ProfileFeign profileFeign;

    @Value("${oauth.naver.client_id}")
    private String client_id;
    @Value("${oauth.naver.client_secret}")
    private String client_secret;

    public ProfileResponseDto loginService(String code, String state) {
        ResponseDto responseDto =
                loginFeign.login("authorization_code", client_id, client_secret, code, state);
        String accessToken = "Bearer " + responseDto.access_token;
        log.info("accessToken = {}" , accessToken);
        ProfileResponseDto profileResponseDto = profileFeign.getProfile(accessToken);
        log.info("email = {}", profileResponseDto.getResponse().getEmail());
        return profileResponseDto;
    }
}
