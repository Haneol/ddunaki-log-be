package com.blog.domain.comment.service;

import com.blog.domain.comment.domain.Comment;
import com.blog.domain.comment.dto.CommentReqDto;
import com.blog.domain.comment.dto.CommentResDto;
import com.blog.domain.schedule.dto.ScheduleResDto;

import java.util.List;

public interface CommentService {
    CommentResDto addComment(Long postingId, CommentReqDto commentReqDto);

    void deleteComment(Long commentId);

    CommentResDto findOne(Long commentId);

    List<CommentResDto> findCommentByPostId(Long postingId);
}
