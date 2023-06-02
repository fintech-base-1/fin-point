package com.fp.finpoint.domain.member.oauth.kakaooauth.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/finpoint/kakao/auth")
    @ResponseBody
    public ProfileResponseDto getCode(@RequestParam String code) {
        return kakaoService.loginService(code);
    }
}
