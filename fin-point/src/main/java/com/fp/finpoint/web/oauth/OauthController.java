package com.fp.finpoint.web.oauth;

import com.fp.finpoint.domain.oauth.OauthService;
import com.fp.finpoint.domain.oauth.google.GoogleService;
import com.fp.finpoint.domain.oauth.kakao.KakaoService;
import com.fp.finpoint.domain.oauth.naver.NaverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Slf4j
@Controller
public class OauthController {

    private final NaverService naverService;
    private final KakaoService kakaoService;
    private final GoogleService googleService;
    private final OauthService oauthService;

    /* 임시 view */
    @GetMapping("/")
    public String login() {
        return "login";
    }


    @ResponseBody
    @GetMapping("/finpoint/oauth/{id}")
    public String loginUrl(@PathVariable(name = "id") String id) {
        switch (id) {
            case "google":
                return googleService.getRequireUrl();
            case "naver":
                return naverService.getRequireUrl();
            case "kakao":
                return kakaoService.getRequireUrl();
        }
        return id;
    }

    @ResponseBody
    @GetMapping("/finpoint/google/auth")
    public String loginGoogle(@RequestParam(value = "code") String code, HttpServletResponse response) {
        String getToken = googleService.oauthLogin(code);
        Cookie cookie = oauthService.encodeCookie(getToken);
        response.addCookie(cookie);
        return "<script>window.close();</script>";
    }

    @ResponseBody
    @GetMapping("/finpoint/naver/auth")
    public String getCode(@RequestParam String code, @RequestParam String state, HttpServletResponse response) {
        String getToken = naverService.loginService(code, state);
        Cookie cookie = oauthService.encodeCookie(getToken);
        response.addCookie(cookie);
        return "<script>window.close();</script>";
    }

    @ResponseBody
    @GetMapping("/finpoint/kakao/auth")
    public String getCode(@RequestParam String code, HttpServletResponse response) {
        String getToken = kakaoService.loginService(code);
        Cookie cookie = oauthService.encodeCookie(getToken);
        response.addCookie(cookie);
        return "<script>window.close();</script>";
    }
}
