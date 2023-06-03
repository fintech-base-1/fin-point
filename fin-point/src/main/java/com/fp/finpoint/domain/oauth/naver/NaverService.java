package com.fp.finpoint.domain.oauth.naver;

import com.fp.finpoint.domain.member.entity.Member;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.domain.oauth.feign.NaverGetProfileFeign;
import com.fp.finpoint.domain.oauth.feign.NaverLoginFeign;
import com.fp.finpoint.web.oauth.dto.NaverProfileResponseDto;
import com.fp.finpoint.web.oauth.dto.NaverResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NaverService {

    private final NaverLoginFeign naverLoginFeign;
    private final NaverGetProfileFeign naverGetProfileFeign;
    private final MemberRepository memberRepository;

    @Value("${oauth.naver.client_id}")
    private String client_id;
    @Value("${oauth.naver.client_secret}")
    private String client_secret;

    public NaverProfileResponseDto loginService(String code, String state) {
        NaverResponseDto naverResponseDto =
                naverLoginFeign.login("authorization_code", client_id, client_secret, code, state);
        String accessToken = "Bearer " + naverResponseDto.getAccess_token();
        log.info("accessToken = {}" , accessToken);
        NaverProfileResponseDto naverProfileResponseDto = naverGetProfileFeign.getProfile(accessToken);
        log.info("email = {}", naverProfileResponseDto.getResponse().getEmail());
        log.info("nickname = {}", naverProfileResponseDto.getResponse().getNickname());
        oauthJoin(naverProfileResponseDto.getResponse().getEmail());
        return naverProfileResponseDto;
    }

    public void oauthJoin(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            return;
        }
        Member member = Member.builder().email(email).build();
        memberRepository.save(member);
    }
}
