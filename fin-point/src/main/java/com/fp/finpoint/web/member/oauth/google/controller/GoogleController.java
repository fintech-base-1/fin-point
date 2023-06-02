package com.fp.finpoint.web.member.oauth.google.controller;

import com.fp.finpoint.web.member.oauth.google.dto.GoogleInfoDto;
import com.fp.finpoint.web.member.oauth.google.dto.GoogleRequestDto;
import com.fp.finpoint.web.member.oauth.google.dto.GoogleResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin("*")
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
    @RequestMapping(value="/api/v1/oauth2/google", method = RequestMethod.POST)
    public String loginUrlGoogle(){
        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri=http://localhost:8080/oauth/google&response_type=code&scope=email%20profile%20openid&access_type=offline";
        System.out.println("reqUrl : "+reqUrl);
        return reqUrl;
    }

    @ResponseBody
    @RequestMapping(value="/oauth/google", method = RequestMethod.GET)
    public String loginGoogle(@RequestParam(value = "code") String authCode){
        RestTemplate restTemplate = new RestTemplate();
        GoogleRequestDto googleOAuthRequestParam = GoogleRequestDto
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientPw)
                .code(authCode)
                .redirectUri("http://localhost:8080/oauth/google")
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
        System.out.println("받아온 이메일 확인: "+email);
        System.out.println("받아온 이름 확인: "+name);
        return "<script>window.close();</script>";
    }



}
