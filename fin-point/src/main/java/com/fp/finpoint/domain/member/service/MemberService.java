package com.fp.finpoint.domain.member.service;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import com.fp.finpoint.util.EmailSenderService;
import com.fp.finpoint.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EmailSenderService emailSenderService;

    public void registerMember(MemberDto memberDto) {
        // 검증
        verifyExistEmail(memberDto.getEmail());

        // 암호화 처리
        String salt = PasswordEncoder.generateSalt();
        String password = PasswordEncoder.hashPassword(memberDto.getPassword(), salt);

        // 저장
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(password)
                .salt(salt)
                .build();

        memberRepository.save(member);
        log.info("# Successful Member Registration!");
    }

    public void doLogin(MemberDto memberDto) throws MessagingException {
        Member member;
        member = inspectEmailExistence(memberDto.getEmail());
        verifyPassword(memberDto.getPassword(), member);
        transferEmail(member);
    }

    private Member inspectEmailExistence(String knockEmail) {
        return memberRepository.findByEmail(knockEmail).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
        );
    }

    private static void verifyPassword(String knockPassword, Member member) {
        String expect = PasswordEncoder.hashPassword(knockPassword, member.getSalt());
        String password = member.getPassword();

        if (!expect.equals(password)) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_WRONG_PASSWORD);
        }
    }

    private void transferEmail(Member member) throws MessagingException {
        String code = emailSenderService.sendHtmlMessageWithInlineImage(member.getEmail());
        member.assignCode(code);
        memberRepository.save(member);
        log.info("# Member Code Save");
    }

    private void verifyExistEmail(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(member -> {
                    throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_EXISTS);
                });
    }

    public Member checkCode(String code) {
        Member member = memberRepository.findByCode(code).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_WRONG_CODE)
        );
        log.info("Code !!");

        return member;
    }
}
