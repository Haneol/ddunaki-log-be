package com.blog.domain.posting.exception;

public class PostingSpaceNotFoundException extends RuntimeException{
    public PostingSpaceNotFoundException() {super("포스팅 관련 스페이스 조회 불가");}
    public PostingSpaceNotFoundException(String message) {
        super(message);
    }
}
