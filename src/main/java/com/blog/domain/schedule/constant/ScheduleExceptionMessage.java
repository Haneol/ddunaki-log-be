package com.blog.domain.schedule.constant;

public enum ScheduleExceptionMessage {

    SCHEDULE_NOT_FOUND("해당 일정를 찾을 수 없습니다."),
    SCHEDULE_USER_NOT_FOUND("해당 일정의 유저를 찾을 수 없습니다."),
    SCHEDULE_SPACE_NOT_FOUND("해당 일정의 스페이스를 찾을 수 없습니다."),
    NO_PERMISSION("이 작업을 수행할 권한이 없습니다.");
    private String message;

    ScheduleExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {return message;}
}
