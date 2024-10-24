package com.blog.domain.schedule.exception;

public class ScheduleDuplicatedException extends RuntimeException {
    public ScheduleDuplicatedException() {super("일정 중복");}
    public ScheduleDuplicatedException(String message) {
        super(message);
    }
}
