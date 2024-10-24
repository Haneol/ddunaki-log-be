package com.blog.domain.posting.constant;

public enum PostingResponseMessage {
    ADD_POSTING("포스팅 작성 완료했습니다."),
    UPDATE_POSTING("포스팅 수정 완료했습니다."),
    DELETE_POSTING("포스팅 삭제 완료했습니다."),
    FIND_ONE_POSTING("포스팅 하나 조회 완료했습니다."),
    FIND_ALL_POSTING("포스팅 전체 조회 완료했습니다.");

    private final String message;

    PostingResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
