package com.fp.finpoint.web.member.controller;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity<HttpStatus> login(@Valid @RequestBody MemberDto memberDto) {
        memberService.doLogin(memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
