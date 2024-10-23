package com.blog.domain.posting.api;

import com.blog.domain.posting.constant.PostingResponseMessage;
import com.blog.domain.posting.dto.PostingReqDto;
import com.blog.domain.posting.dto.PostingResDto;
import com.blog.domain.posting.dto.PostingTitleResDto;
import com.blog.domain.posting.dto.UpdatePostingReqDto;
import com.blog.domain.posting.service.PostingService;
import com.blog.global.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posting")
public class PostingController {
    private final PostingService postingService;

    @PostMapping("")
    public ResponseEntity<PostingResDto> addPosting(@RequestBody PostingReqDto postingReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postingService.addPosting(postingReqDto));
    }

    @PutMapping("/{postingId}")
    public ResponseEntity<PostingResDto> updatePosting(@PathVariable Long postingId, @RequestBody UpdatePostingReqDto updatePostingReqDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postingService.updatePosting(postingId, updatePostingReqDto));
    }

    @DeleteMapping("/{postingId}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long postingId) {
        postingService.deletePosting(postingId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(MessageDto.msg(PostingResponseMessage.DELETE_POSTING.getMessage()));
    }

    @GetMapping("/{postingId}")
    public ResponseEntity<PostingTitleResDto> findPostingTitle(@PathVariable Long postingId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postingService.findPostingTitle(postingId));
    }

    @GetMapping("/{postingId}")
    public ResponseEntity<PostingResDto> findPosting(@PathVariable Long postingId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postingService.findOnePosting(postingId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostingResDto>> findAllSearch(
            @RequestParam(required = false) String writerNickname,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String nationCode,
            @RequestParam(required = false) String cityCode) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(postingService.findAllSearch(writerNickname, title, nationCode, cityCode));
    }

    @GetMapping("/space/{spaceId}")
    public ResponseEntity<List<PostingResDto>> findAllPostingBySpaceId(@PathVariable Long spaceId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postingService.findAllPostingBySpaceId(spaceId));
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<PostingResDto>> findAllPostingByScheduleId(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postingService.findAllPostingByScheduleId(scheduleId));
    }

    // 댓글 수 확인 API
    @GetMapping("/{postingId}/commentCount")
    public ResponseEntity<Integer> getCommentCount(@PathVariable Long postingId) {
        int commentCount = postingService.getCommentCnt(postingId);
        return ResponseEntity.ok(commentCount);
    }
}
