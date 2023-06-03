package com.fp.finpoint.web.oauth;

import com.fp.finpoint.domain.oauth.kakao.KakaoService;
import com.fp.finpoint.domain.oauth.naver.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class OauthController {

    private final NaverService naverService;
    private final KakaoService kakaoService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/finpoint/naver/auth")
    @ResponseBody
    public String getCode(@RequestParam String code, @RequestParam String state) {

        naverService.loginService(code, state);
        return "<script>window.close();</script>";
    }

    @GetMapping("/finpoint/kakao/auth")
    @ResponseBody
    public String getCode(@RequestParam String code) {

        kakaoService.loginService(code);
        return "<script>window.close();</script>";
    }
}
