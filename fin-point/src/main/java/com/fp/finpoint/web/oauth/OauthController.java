package com.fp.finpoint.web.oauth;

import com.fp.finpoint.domain.oauth.kakao.KakaoService;
import com.fp.finpoint.domain.oauth.naver.NaverService;
import com.fp.finpoint.web.oauth.naver.dto.ProfileResponseDto;
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
    public ProfileResponseDto getCode(@RequestParam String code, @RequestParam String state) {

        return naverService.loginService(code, state);
    }

    @GetMapping("/finpoint/kakao/auth")
    @ResponseBody
    public com.fp.finpoint.web.oauth.kakao.dto.ProfileResponseDto getCode(@RequestParam String code) {
        return kakaoService.loginService(code);
    }
}
