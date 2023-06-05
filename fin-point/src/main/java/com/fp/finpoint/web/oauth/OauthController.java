package com.fp.finpoint.web.oauth;

import com.fp.finpoint.domain.oauth.google.GoogleService;
import com.fp.finpoint.domain.oauth.kakao.KakaoService;
import com.fp.finpoint.domain.oauth.naver.NaverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OauthController {

    private final NaverService naverService;
    private final KakaoService kakaoService;
    private final GoogleService googleService;

    @ResponseBody
    @PostMapping("/finpoint/oauth/{id}")
    public String loginUrlGoogle(@PathVariable(name = "id") String id) {
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
    public String loginGoogle(@RequestParam(value = "code") String authCode, HttpServletResponse response) {

        String jwtToken = googleService.oauthLogin(authCode);
        response.addHeader("Authorization", jwtToken);
        return "<script>window.close();</script>";
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/finpoint/naver/auth")
    @ResponseBody
    public String getCode(@RequestParam String code, @RequestParam String state, HttpServletResponse response) {

        String getToken = naverService.loginService(code, state);
        response.addHeader("Authorization", getToken);
        return "<script>window.close();</script>";
    }

    @GetMapping("/finpoint/kakao/auth")
    @ResponseBody
    public String getCode(@RequestParam String code, HttpServletResponse response) {

        String getToken = kakaoService.loginService(code);
        response.addHeader("Authorization", getToken);
        return "<script>window.close();</script>";
    }
}
