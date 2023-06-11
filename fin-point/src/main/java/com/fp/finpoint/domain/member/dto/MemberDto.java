package com.fp.finpoint.domain.member.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String nickname;

    public static class Code {
        @Getter
        @NotBlank
        String code;
    }
}
