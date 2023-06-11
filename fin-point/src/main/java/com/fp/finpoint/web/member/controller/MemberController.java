package com.fp.finpoint.web.member.controller;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.service.MemberService;
import com.fp.finpoint.global.util.CookieUtil;
import com.fp.finpoint.global.util.JwtUtil;
import com.fp.finpoint.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final CookieUtil cookieUtil;

    @GetMapping("/finpoint/join")
    public String join() {
        return "join";
    }

    @ResponseBody
    @PostMapping("/finpoint/join")
    public ResponseEntity<HttpStatus> join(@Valid @RequestBody MemberDto memberDto) {
        memberService.registerMember(memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/finpoint/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @PostMapping("/finpoint/login")
    public ResponseEntity<HttpStatus> login(@Valid @RequestBody MemberDto memberDto) throws MessagingException {
        memberService.doLogin(memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/finpoint/mail-confirm")
    public String mailconfirm(){return "mail-confirm";}

    @ResponseBody
    @PostMapping("/finpoint/mail-confirm")
    public ResponseEntity<HttpStatus> code(@Valid @RequestBody MemberDto.Code code, HttpServletResponse response) {
        String loginUserEmail = memberService.checkCode(code.getCode().trim());
        memberService.registerTokenInCookie(loginUserEmail, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/finpoint/assign-seller")
    public ResponseEntity<HttpStatus> assignSeller(HttpServletRequest request) throws UnsupportedEncodingException {
        String accessToken = CookieUtil.getAccessToken(request.getCookies());
        String loginUserEmail = JwtUtil.getEmail(accessToken);
        memberService.addSeller(loginUserEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/finpoint/logout")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        cookieUtil.deleteCookie(JwtUtil.AUTHORIZATION,response);
        cookieUtil.deleteCookie(JwtUtil.REFRESH,response);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

}
