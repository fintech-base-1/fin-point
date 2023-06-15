package com.fp.finpoint.web.openbank.Controller;

import com.fp.finpoint.domain.openbank.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

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
    @ResponseBody
    public void requestToken(@RequestParam("code") String code,
                               @RequestParam("scope") String scope,
                               @RequestParam("state") String state,
                               HttpServletRequest request) throws UnsupportedEncodingException {
        log.info("code = {}", code);
        tokenService.saveToken(code, request);
    }

    @GetMapping("/finpoint/bank")
    public String bankMain() {
        return "banking";
    }

    @GetMapping("/finpoint/bank/account")
    @ResponseBody
    public void getAccountList(HttpServletRequest request) throws UnsupportedEncodingException {
        tokenService.getAccountList(request);
    }
}
