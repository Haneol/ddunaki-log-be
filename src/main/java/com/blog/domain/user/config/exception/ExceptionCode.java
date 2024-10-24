package com.blog.domain.user.config.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_ROLE_DOES_NOT_EXISTS(404, "회원의 권한이 존재하지 않습니다."),
    MEMBER_EXISTS(404, "이미 존재하는 회원입니다."),
    INVALID_EMAIL(400,"유효하지 않은 이메일 주소 형식입니다."),
    INVALID_PW(400,"유효하지 않은 비밀번호 형식입니다."),
    AUTH_CODE_IS_NOT_SAME(404, "인증 번호가 일치하지 않습니다."),
    UNABLE_TO_SEND_EMAIL(404, "메일을 전송할 수 없습니다."),
    FOLLOW_NOT_FOUND(404, "팔로우를 하지 않았습니다."),
    FOLLOW_EXISTS(404, "이미 팔로우 했습니다."),
    NO_SUCH_ALGORITHM(400, "알고리즘을 찾을 수 없습니다.");
    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
