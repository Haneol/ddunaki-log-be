package com.blog.domain.comment.api;

import com.blog.domain.comment.constant.CommentResponseMessage;
import com.blog.domain.comment.dto.CommentReqDto;
import com.blog.domain.comment.dto.CommentResDto;
import com.blog.domain.comment.service.CommentService;
import com.blog.global.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postingId}")
    public ResponseEntity<CommentResDto> addComment(@PathVariable Long postingId, @RequestBody CommentReqDto commentReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.addComment(postingId, commentReqDto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageDto> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(MessageDto.msg(CommentResponseMessage.DELETE_COMMENT.getMessage()));
    }

    @GetMapping("/posting/{postingId}")
    public ResponseEntity<List<CommentResDto>> findAllCommentsByPostId(@PathVariable Long postingId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.findCommentByPostId(postingId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResDto> findOneComment(@PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.findOne(commentId));
    }
}
