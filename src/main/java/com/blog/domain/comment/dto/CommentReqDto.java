package com.blog.domain.comment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentReqDto {

    private String writerNickname;
    private String content;
}
