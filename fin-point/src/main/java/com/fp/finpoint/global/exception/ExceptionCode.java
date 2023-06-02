package com.fp.finpoint.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다.");

    @Getter
    final int code;

    @Getter
    final String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
