package com.fp.finpoint.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    // member
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    MEMBER_ALREADY_EXISTS(404, "이미 존재하는 회원입니다."),
    MEMBER_WRONG_PASSWORD(400, "패스워드가 틀립니다.");

    @Getter
    final int code;

    @Getter
    final String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
