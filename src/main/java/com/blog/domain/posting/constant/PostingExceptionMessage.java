package com.blog.domain.posting.constant;

public enum PostingExceptionMessage {
    POSTING_NOT_FOUND("해당 ID의 포스팅을 찾을 수 없습니다."),
    POSTING_USER_NOT_FOUND("해당 포스팅의 유저를 찾을 수 없습니다."),
    POSTING_SPACE_NOT_FOUND("해당 포스팅의 스페이스를 찾을 수 없습니다."),
    POSTING_SCHEDULE_NOT_FOUND("해당 포스팅의 스케줄을 찾을 수 없습니다."),
    MEMBER_NOT_FOUND("해당 유저는 스페이스의 멤버가 아닙니다."),
    NO_PERMISSION("이 작업을 수행할 권한이 없습니다.");

    private String message;

    PostingExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {return message;}
}
