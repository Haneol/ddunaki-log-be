package com.blog.domain.comment.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {super("댓글 조회 불가");}
    public CommentNotFoundException(String message) {super(message);}
}
