package com.fp.finpoint.web.member.oauth.google.controller;

import com.fp.finpoint.web.member.oauth.google.dto.GoogleInfoDto;
import com.fp.finpoint.web.member.oauth.google.dto.GoogleRequestDto;
import com.fp.finpoint.web.member.oauth.google.dto.GoogleResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class GoogleController {
    @Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.client.pw}")
    private String googleClientPw;

    @GetMapping(value = "/")
    public String viewTest(){
        return "main";
    }

    @ResponseBody
    @PostMapping("/api/v1/oauth2/google")
    public String loginUrlGoogle(){
        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri=http://localhost:8080/finpoint/google/auth&response_type=code&scope=email%20profile%20openid&access_type=offline";
        return reqUrl;
    }

    @ResponseBody
    @GetMapping("/finpoint/google/auth")
    public String loginGoogle(@RequestParam(value = "code") String authCode){
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
        String email=resultEntity2.getBody().getEmail();
        String name=resultEntity2.getBody().getName();
        System.out.println(email);
        System.out.println(name);
        log.info("email address = {}", email);
        return "<script>window.close();</script>";
    };
}
