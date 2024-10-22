package com.blog.domain.space.exception;

import com.blog.domain.space.constant.SpaceExceptionMessage;

public class SpaceNotFoundException extends RuntimeException {
    public SpaceNotFoundException() {super("스페이스 조회 불가");}
    public SpaceNotFoundException(String message) {
        super(message);
    }

}
