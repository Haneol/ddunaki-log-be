package com.blog.domain.schedule.exception;

public class ScheduleUserNotFoundException extends RuntimeException {
    public ScheduleUserNotFoundException() {super("일정 관련 유저 조회 불가");}
    public ScheduleUserNotFoundException(String message) {
        super(message);
    }
}
