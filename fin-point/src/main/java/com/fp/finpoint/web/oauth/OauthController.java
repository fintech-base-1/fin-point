package com.fp.finpoint.web.oauth;

import com.fp.finpoint.domain.oauth.google.GoogleService;
import com.fp.finpoint.domain.oauth.kakao.KakaoService;
import com.fp.finpoint.domain.oauth.naver.NaverService;
import com.fp.finpoint.global.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Slf4j
@Controller
public class OauthController {

    private final NaverService naverService;
    private final KakaoService kakaoService;
    private final GoogleService googleService;

    /* 임시 view */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/join")
    public String join() {
        return "join";
    }
    @GetMapping("/finish")
    public String finish() {
        return "finish";
    }
    @GetMapping("/mailConfirm")
    public String mailConfirm() {
        return "mailConfirm";
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
    public ResponseEntity<HttpStatus> loginGoogle(@RequestParam(value = "code") String code, HttpServletResponse response) {
        String getToken = googleService.oauthLogin(code);
        CookieUtil.setCookieInHeader(response,getToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/finpoint/naver/auth")
    public ResponseEntity<HttpStatus> getCode(@RequestParam String code, @RequestParam String state, HttpServletResponse response) {
        String getToken = naverService.loginService(code, state);
        CookieUtil.setCookieInHeader(response,getToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/finpoint/kakao/auth")
    public ResponseEntity<HttpStatus> getCode(@RequestParam String code, HttpServletResponse response) {
        String getToken = kakaoService.loginService(code);
        CookieUtil.setCookieInHeader(response,getToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
