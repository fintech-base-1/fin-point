package com.fp.finpoint.domain.member.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
