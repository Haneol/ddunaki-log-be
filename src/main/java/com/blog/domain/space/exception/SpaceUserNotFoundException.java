package com.blog.domain.space.exception;

public class SpaceUserNotFoundException extends RuntimeException {
  public SpaceUserNotFoundException() {super("스페이스 관련 유저 조회 불가");}
  public SpaceUserNotFoundException(String message) {
      super(message);
  }
}
