package com.fp.finpoint.domain.member.service;

import com.fp.finpoint.domain.member.dto.MemberDto;
import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.entity.Role;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.domain.oauth.OauthClient;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import com.fp.finpoint.global.util.EmailSenderService;
import com.fp.finpoint.global.util.PasswordEncoder;
import com.fp.finpoint.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EmailSenderService emailSenderService;
    private final RedisUtil redisUtil;

    public void registerMember(MemberDto memberDto) {
        // 검증
        isExistEmail(memberDto.getEmail());

        // 암호화
        String salt = PasswordEncoder.generateSalt();
        String password = PasswordEncoder.hashPassword(memberDto.getPassword(), salt);

        // 권한
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);

        // 등록
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(password)
                .salt(salt)
                .roles(roles)
                .oauthClient(OauthClient.NOTHING)
                .build();

        memberRepository.save(member);
        log.info("# Successful Member Registration!");
    }

    public void oauthJoin(String email, OauthClient oauthClient) {
        isExistEmail(email);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        Member member = Member.builder().
                email(email)
                .roles(roles)
                .oauthClient(oauthClient)
                .build();

        memberRepository.save(member);
    }

    public void doLogin(MemberDto memberDto) {
        Member savedMember = inspectEmailExistence(memberDto.getEmail());
        OauthClient oauthClient = savedMember.getOauthClient();
        switch (oauthClient) {
            case NOTHING:
                verifyPassword(memberDto.getPassword(), savedMember);
                String verificationCode = transferEmail(savedMember);
                setMemberInRedisWithCode(savedMember, verificationCode);
                break;
            case GOOGLE:
                throw new BusinessLogicException(ExceptionCode.MEMBER_REGISTRY_GOOGEL);
            case NAVER:
                throw new BusinessLogicException(ExceptionCode.MEMBER_REGISTRY_NAVER);
            case KAKAO:
                throw new BusinessLogicException(ExceptionCode.MEMBER_REGISTRY_KAKAO);
        }
    }

    public String checkCode(String code) {
        ValueOperations<String, String> operations = redisUtil.getValueOperations();
        String value = redisUtil.getRedisValue(operations, code);
        isCodeExpired(value);
        return value;
    }

    private static void isCodeExpired(String value) {
        if (value == null) {
            throw new BusinessLogicException(ExceptionCode.CODE_EXPIRED);
        }
    }

    public void addSeller(String loginUserEmail) {
        Member member = inspectEmailExistence(loginUserEmail);
        Set<Role> roles = member.getRoles();
        roles.add(Role.ROLE_SELLER);
        memberRepository.save(member);
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

    private String transferEmail(Member member) {
        String email = member.getEmail();
        String code = emailSenderService.sendHtmlMessageWithInlineImage(email);
        log.info("# Authentication Mail Transfer!");
        return code;
    }

    private void setMemberInRedisWithCode(Member member, String code) {
        String email = member.getEmail();
        ValueOperations<String, String> operations = redisUtil.getValueOperations();
        redisUtil.setRedisValue(operations, code, email, 10, TimeUnit.MINUTES);
        log.info("# Code set in Redis!");
    }

    private void isExistEmail(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(member -> {
                    throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_EXISTS);
                });
    }
}
