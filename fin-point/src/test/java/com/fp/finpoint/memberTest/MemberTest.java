package com.fp.finpoint.memberTest;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void memberPasswordTest() throws MessagingException {
        String email = "dongwoo1404@kakao.com";
        String password = "hahahoho!123";

        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(email);
        memberDto.setPassword(password);
        memberService.registerMember(memberDto);
        Member savedMember = memberRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("error"));

//        Assertions.assertThat(savedMember.getEmail()).isEqualTo(email);
//        Assertions.assertThat(savedMember.getPassword()).isEqualTo(password);

//        String s = PasswordEncoder.hashPassword(password, savedMember.getSalt());

//        Assertions.assertThat(password1()).isEqualTo(s);
//        Assertions.assertThat(s).isEqualTo(s트1);

        memberService.doLogin(memberDto);

//        Assertions.assertThat()
//        PasswordEncoder.hashPassword(password, )

    }
}
