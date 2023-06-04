package com.fp.finpoint.web.openbank.Controller;

import com.fp.finpoint.domain.openbank.service.TokenService;
import com.fp.finpoint.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/finpoint/bank/auth")
    @ResponseBody
    public String getBankUrl() {
        return tokenService.getRequireUrl();
    }

    @GetMapping("/requesttoken")
    public void requestToken(@RequestParam("code") String code,
                             @RequestParam("state") String state) {
        log.info("code = {}", code);
        tokenService.saveToken(code);
    }

    @GetMapping("/finpoint/bank")
    public String bankMain() {
        return "banking";
    }

    @GetMapping("/finpoint/bank/account")
    public void getAccountList(HttpServletRequest request) {
        String token = request.getHeader(JwtUtil.AUTHORIZATION);
        //todo: Http Request에서 jwt 정보 받아와 decode 이후 email로 memberId를 찾아와야함
        tokenService.getAccountList(1L);
    }
}
