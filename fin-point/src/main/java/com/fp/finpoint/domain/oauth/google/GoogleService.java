package com.fp.finpoint.domain.oauth.google;

import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.global.jwt.JwtUtil;
import com.fp.finpoint.web.oauth.dto.google.GoogleInfoDto;
import com.fp.finpoint.web.oauth.dto.google.GoogleRequestDto;
import com.fp.finpoint.web.oauth.dto.google.GoogleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleService {

    @Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.client.pw}")
    private String googleClientPw;

    private final MemberRepository memberRepository;

    public String getRequireUrl() {
        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri=http://localhost:8080/finpoint/google/auth&response_type=code&scope=email%20profile%20openid&access_type=offline";
        return reqUrl;
    }

    public String oauthLogin(String authCode) {
        RestTemplate restTemplate = new RestTemplate();
        GoogleRequestDto googleOAuthRequestParam = GoogleRequestDto
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientPw)
                .code(authCode)
                .redirectUri("http://localhost:8080/finpoint/google/auth")
                .grantType("authorization_code").build();
        ResponseEntity<GoogleResponseDto> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponseDto.class);
        String jwtToken=resultEntity.getBody().getId_token();
        Map<String, String> map=new HashMap<>();
        map.put("id_token",jwtToken);
        ResponseEntity<GoogleInfoDto> resultEntity2 = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
                map, GoogleInfoDto.class);
        String email = resultEntity2.getBody().getEmail();
        String name = resultEntity2.getBody().getName();
        log.info("email = {}", email);
        log.info("name = {}", name);
        oauthJoin(email);

        return JwtUtil.createAccessToken(email);
    }

    public void oauthJoin(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            return;
        }
        Member member = Member.builder().email(email).build();
        memberRepository.save(member);
    }
}
