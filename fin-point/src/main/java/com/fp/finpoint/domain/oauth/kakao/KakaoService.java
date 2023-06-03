package com.fp.finpoint.domain.oauth.kakao;

import com.fp.finpoint.domain.oauth.feign.LoginFeign;
import com.fp.finpoint.domain.oauth.feign.ProfileFeign;
import com.fp.finpoint.web.oauth.kakao.dto.ProfileResponseDto;
import com.fp.finpoint.web.oauth.kakao.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {

    private final LoginFeign loginFeign;
    private final ProfileFeign profileFeign;

    @Value("${oauth.kakao.client_id}")
    private String client_id;
    @Value("${oauth.kakao.client_secret}")
    private String client_secret;
    @Value("${oauth.kakao.callback}")
    private String redirect_uri;

    public ProfileResponseDto loginService(String code) {
        ResponseDto responseDto =
                loginFeign.login("authorization_code",client_id,redirect_uri,code);
        String accessToken = "Bearer " + responseDto.getAccess_token();
        log.info("accessToken = {}" , accessToken);
        ProfileResponseDto profileResponseDto = profileFeign.getProfile(accessToken, "application/x-www-form-urlencoded;charset=utf-8");
        log.info("email = {}", profileResponseDto.getKakao_account().getEmail());
        return profileResponseDto;
    }

}
