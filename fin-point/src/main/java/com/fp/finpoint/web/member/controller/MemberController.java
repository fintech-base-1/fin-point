package com.fp.finpoint.web.member.controller;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.service.MemberService;
import com.fp.finpoint.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/finpoint/join")
    public ResponseEntity<HttpStatus> join(@Valid @RequestBody MemberDto memberDto) {
        memberService.registerMember(memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/finpoint/login")
    public ResponseEntity<HttpStatus> login(@Valid @RequestBody MemberDto memberDto, HttpServletResponse response) throws MessagingException {
        memberService.doLogin(memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // url mail-confirm 수정필요
    @PostMapping("/finpoint/mail-confirm")
    public ResponseEntity<HttpStatus> code(@Valid @RequestBody MemberDto.Code code, HttpServletResponse response) throws UnsupportedEncodingException {
//        Member member = memberService.checkCode(code.getCode());
//        JwtUtil.setAccessToken(JwtUtil.createAccessToken(member.getEmail()), response);
        memberService.test(code, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 권한 추가 컨트롤러
    @PostMapping("/finpoint/assign-seller")
    public ResponseEntity<HttpStatus> assignSeller(HttpServletRequest request) throws UnsupportedEncodingException {
        String accessToken = JwtUtil.getAccessToken(request.getCookies());
        String loginUserEmail = JwtUtil.getEmail(accessToken);
        memberService.addSeller(loginUserEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
