package com.blog.domain.posting.exception;

public class PostingDuplicatedException extends RuntimeException {
  public PostingDuplicatedException() {super("포스팅 중복");}
  public PostingDuplicatedException(String message) {
        super(message);
    }
}
