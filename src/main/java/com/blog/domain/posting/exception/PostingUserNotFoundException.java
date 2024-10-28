package com.blog.domain.posting.exception;

public class PostingUserNotFoundException extends RuntimeException {
    public PostingUserNotFoundException() {super("포스팅 관련 유저 조회 불가");}
    public PostingUserNotFoundException(String message) {
        super(message);
    }
}
