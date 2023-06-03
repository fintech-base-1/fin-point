package com.fp.finpoint.web.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaverProfileResponseDto {

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
