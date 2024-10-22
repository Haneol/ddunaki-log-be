package com.blog.domain.space.constant;

public enum SpaceResponseMessage {
    UPDATE_SPACE("스페이스 수정 완료했습니다."),
    DELETE_SPACE("스페이스 삭제 완료했습니다."),
    FIND_ONE_SPACE("스페이스 조회 완료했습니다."),

    ADD_MEMBER("멤버 추가 완료했습니다."),
    DELETE_MEMBER("멤버 강퇴 완료했습니다.");

    private final String message;

    SpaceResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
