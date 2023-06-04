package com.fp.finpoint.domain.openbank;

import com.fp.finpoint.web.openbank.dto.AccountResponseDto;
import com.fp.finpoint.web.openbank.dto.TokenResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feign", url = "${external.url}")
public interface BankingFeign {

    @PostMapping(path = "${external.path}", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    TokenResponseDto requestToken(@RequestParam("code") String code,
                                  @RequestParam("client_id") String client_id,
                                  @RequestParam("client_secret") String client_secret,
                                  @RequestParam("redirect_uri") String redirect_uri,
                                  @RequestParam("grant_type") String grant_type);

    @GetMapping(path = "${external.account.path}", consumes = "application/x-www-form-urlencoded", produces = "application/json",
            headers = "")
    AccountResponseDto getAccountList(@RequestHeader(value = "Authorization") String accessToken,
                                      @RequestParam("user_seq_no") String user_seq_no,
                                      @RequestParam("include_cancel_yn") String include_cancel_yn,
                                      @RequestParam("sort_order") String sort_order);
}
