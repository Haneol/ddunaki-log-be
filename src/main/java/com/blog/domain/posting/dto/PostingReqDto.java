package com.blog.domain.posting.dto;

import com.blog.domain.posting.constant.PostingAccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostingReqDto {

    private String title;
    private String content;
    private PostingAccessLevel accessLevel;
    private String mainImgUrl;
    private Long scheduleId;
    private Long spaceId;

}
