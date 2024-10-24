package com.blog.domain.space.exception;

public class SpaceDuplicatedException extends RuntimeException {
    public SpaceDuplicatedException() {super("스페이스 중복");}
    public SpaceDuplicatedException(String message) {
        super(message);
    }
}
