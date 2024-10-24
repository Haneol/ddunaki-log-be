package com.blog.domain.comment.constant;

public enum CommentExceptionMessage {
    COMMENT_NOT_FOUND("댓글 조회 실패");
    private final String message;

    CommentExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
