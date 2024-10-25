package com.blog.domain.posting.dto;

import com.blog.domain.posting.constant.PostingAccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostingReqDto {

    private String title;
    private String content;
    private PostingAccessLevel accessLevel;
    private String mainImgUrl;
    private Long scheduleId;
    private Long spaceId;

}
