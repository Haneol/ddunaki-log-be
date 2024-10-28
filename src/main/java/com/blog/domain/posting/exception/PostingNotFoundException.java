package com.blog.domain.posting.exception;

public class PostingNotFoundException extends RuntimeException {
    public PostingNotFoundException() {super("포스팅 조회 불가");}
    public PostingNotFoundException(String message) {super(message);}
}
