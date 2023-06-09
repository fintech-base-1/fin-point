package com.fp.finpoint.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    // member
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    MEMBER_ALREADY_EXISTS(404, "이미 존재하는 회원입니다."),
    MEMBER_WRONG_PASSWORD(400, "패스워드가 틀립니다."),
    MEMBER_WRONG_CODE(400, "입력한 코드가 틀립니다."),

    // oauth
    MEMBER_REGISTRY_GOOGEL(400, "이미 구글인증으로 가입한 회원입니다"),
    MEMBER_REGISTRY_NAVER(400, "이미 네이버인증으로 가입한 회원입니다"),
    MEMBER_REGISTRY_KAKAO(400, "이미 카카오인증으로 가입한 회원입니다"),

    // mail
    EMAIL_TRANSFER_FAIL(500, "이메일 전송이 실패했습니다"),
    CODE_EXPIRED(400, "입력한 코드가 만료됐습니다."),

    // piece
    PIECE_NOT_FOUND(404, "존재하지 않는 조각입니다."),
    PIECE_NOT_ENOUGH(400, "조각이 모자랍니다."),

    //token
    TOKEN_NOT_FOUND(404, "존재하지 않는 토큰입니다."),
    VALUE_NOT_FOUND(404, "원하는 쿠키 값이 없습니다"),

    // redis
    REDIS_CONNECTION_ERROR(500, "Redis 서버와의 연결에 문제가 있습니다."),
    REDIS_COMMAND_ERROR(500, "Redis 명령어 실행에 문제가 있습니다."),

    //invset
    INVEST_NOT_FOUND(404, "존재하지 않는 상품입니다.");

    @Getter
    final int code;

    @Getter
    final String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
