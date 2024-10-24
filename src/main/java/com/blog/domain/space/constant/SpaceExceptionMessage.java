package com.blog.domain.space.constant;

public enum SpaceExceptionMessage {
    SPACE_NOT_FOUND("해당 ID의 스페이스를 찾을 수 없습니다."),
    SPACE_USER_NOT_FOUND("해당 스페이스의 유저를 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS("해당 유저는 이미 스페이스의 멤버입니다."),
    MEMBER_LIMIT_EXCEEDED("스페이스의 최대 멤버 수에 도달했습니다."),
    MEMBER_NOT_FOUND("해당 유저는 스페이스의 멤버가 아닙니다."),
    NO_PERMISSION("이 작업을 수행할 권한이 없습니다.");
    private String message;

    SpaceExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {return message;}
}
