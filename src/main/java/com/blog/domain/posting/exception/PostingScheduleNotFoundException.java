package com.blog.domain.posting.exception;

public class PostingScheduleNotFoundException extends RuntimeException {
    public PostingScheduleNotFoundException() {super("포스팅 관련 일정 조회 불가");}
    public PostingScheduleNotFoundException(String message) {
        super(message);
    }
}
