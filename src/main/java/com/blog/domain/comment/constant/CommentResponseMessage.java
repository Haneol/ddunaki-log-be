package com.blog.domain.comment.constant;

public enum CommentResponseMessage {
    CREATE_COMMENT("댓글 등록 완료"),
    DELETE_COMMENT("댓글 삭제 완료");

    private final String message;

    CommentResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
