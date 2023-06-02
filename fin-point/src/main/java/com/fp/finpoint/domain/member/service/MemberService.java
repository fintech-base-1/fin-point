package com.fp.finpoint.domain.member.service;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import com.fp.finpoint.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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

        //TODO: 이메일 인증

        memberRepository.save(member);
    }

    public void doLogin(MemberDto memberDto) {
        String password = memberDto.getPassword();
        // 유무 검증
        Member member = memberRepository.findByEmail(memberDto.getEmail()).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
        );

        // 패스워드 검증
        String salt = member.getSalt();
        String expect = PasswordEncoder.hashPassword(password, salt);
        String real = member.getPassword();

        if (!expect.equals(real)) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_WRONG_PASSWORD);
        }

        //TODO: 성공 시 jwt 토큰 전달

    }

    public void verifyExistEmail(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_EXISTS);
        }
    }
}
