package com.blog.domain.comment.dto;

import com.blog.domain.comment.domain.Comment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CommentResDto {
    private final Long postingId;
    private final Long commentId;
    private final String writerNickname;
    private final String writerEmail;
    private final String content;
    private final LocalDate createdAt;
    private final LocalDate modifiedAt;

    public static CommentResDto entityToResDto(Comment comment) {
        return CommentResDto.builder()
                .commentId(comment.getCommentId())
                .postingId(comment.getPosting().getPostingId())
                .writerNickname(comment.getWriter().getNickname())
                .writerEmail(comment.getWriter().getEmail())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}
