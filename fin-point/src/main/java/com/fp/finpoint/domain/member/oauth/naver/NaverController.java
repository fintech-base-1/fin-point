package com.fp.finpoint.domain.member.oauth.naver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class NaverController {

    private final NaverService naverService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/finpoint/naver/auth")
    @ResponseBody
    public ProfileResponseDto getCode(@RequestParam String code, @RequestParam String state) {
        return naverService.loginService(code, state);
        //123456
    }
}
