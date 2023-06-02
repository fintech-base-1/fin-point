package com.fp.finpoint.web.oauth.kakao.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fp.finpoint.web.oauth.kakao.model.KakaoProfile;
import com.fp.finpoint.web.oauth.kakao.model.OAuthToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Controller
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserController {

    @Value("${cos.key}")
    private String cosKey;


    @GetMapping("/oauth/kakao")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public String KakaoCallback(String code)  { // @ResponseBody : Data 를 리턴해주는 컨트롤러 함수 , String code 는 url 에서 code로 받아오는값(쿼리스트링)

        // POST 방식으로 key=value 데이터를 요청(카카오를 향해)  (!= a태그로 보내는방식은 무조건 GET방식)

        RestTemplate rt = new RestTemplate(); // RestTemplate 라이브러리를 사용하면 쉬움.(기존에는 HttpsURLConnection 을 사용하는데 번거로움 , Retrofit2/OkHttp/RestTemplate 을 사용할 수 있음)

        // HttpHeader 오브젝트 생성.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성.
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        //TODO: key=value 값들을 상수화 할것.
        params.add("grant_type","authorization_code");
        params.add("client_id","a71e5f38f49a8dc19d07c70e79802404");
        params.add("redirect_uri","http://localhost:8080/oauth/kakao");
        params.add("code", code);

        // HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =  // HttpEntity: entity , MultiValueMap<>: entity 에 들어가는 값
                new HttpEntity<>(params,headers);

        // Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        System.out.println("response 값 : "+response);
//
        //Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("카카오 엑세스 토큰 :" +oAuthToken.getAccess_token());

        //return "response 값 :"+response.getBody();



        RestTemplate rt2 = new RestTemplate(); // RestTemplate 라이브러리를 사용하면 쉬움.(기존에는 HttpsURLConnection 을 사용하는데 번거로움 , Retrofit2/OkHttp/RestTemplate 을 사용할 수 있음)

        // HttpHeader 오브젝트 생성.
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oAuthToken.getAccess_token()); //Bearer 한칸 띄어쓰기.
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        System.out.println("헤더값 테스트 headers2 :"+headers2);
        // HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =  // HttpEntity: entity , MultiValueMap<>: entity 에 들어가는 값
                new HttpEntity<>(headers2);
        System.out.println("kakaoProfileRequest: "+kakaoProfileRequest);
        // Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        System.out.println("response2.getBody() : "+response2.getBody());

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("카카오 아이디(번호): "+kakaoProfile.getId());
        System.out.println("카카오 아이디(어카운트): "+kakaoProfile.getKakao_account());
        UUID tempPassword = UUID.randomUUID();
        System.out.println("카카오 임시 패스워드 : "+cosKey);

//        Member member = Member.builder().id(kakaoProfile.getId()).password(tempPassword.toString());

//        return "response2 값 :"+response2.getBody();
        return "redirect:/1"; // redirect 를 하려면 @ResponseBody 를 없애야한다.
    }


}

