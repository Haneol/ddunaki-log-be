package com.blog.domain.posting.dto;

import com.blog.domain.posting.constant.PostingAccessLevel;
import com.blog.domain.posting.domain.Posting;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PostingResDto {

    private final Long postingId;
    private final String writerNickname;
    private final String title;
    private final String content;
    private final String profile;
    private final PostingAccessLevel accessLevel;
    private final Integer commentCnt;
    private final String mainImgUrl;
    private final Long scheduleId;
    private final Long spaceId;
    private final LocalDate createdAt;
    private final LocalDate modifiedAt;

    public static PostingResDto entityToResDto(Posting posting) {
        return PostingResDto.builder()
                .postingId(posting.getPostingId())
                .writerNickname(posting.getWriter().getNickName())
                .title(posting.getTitle())
                .content(posting.getContent())  // Markdown 형식의 content
                .accessLevel(posting.getAccessLevel())
                .profile(posting.getWriter().getProfile())
                .commentCnt(posting.getCommentCnt())
                .mainImgUrl(posting.getMainImgUrl())
                .scheduleId(posting.getSchedule().getScheduleId())
                .spaceId(posting.getSpace().getSpaceId())
                .createdAt(posting.getCreatedAt())
                .modifiedAt(posting.getModifiedAt())
                .build();
    }
}
