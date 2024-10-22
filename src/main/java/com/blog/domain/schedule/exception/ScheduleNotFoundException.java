package com.blog.domain.schedule.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException() {super("일정 조회 불가");}
    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
