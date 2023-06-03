package com.fp.finpoint.web.oauth.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto {

    public String resultcode;
    public String message;
    public Response response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {

        public String nickname;
        public String email;
    }
}
