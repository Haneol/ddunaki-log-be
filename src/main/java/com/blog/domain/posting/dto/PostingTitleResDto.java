package com.blog.domain.posting.dto;

import com.blog.domain.posting.domain.Posting;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostingTitleResDto {
    private final Long postingId;
    private final String title;

    public static PostingTitleResDto entityToResDto(Posting posting) {
        return PostingTitleResDto.builder()
                .postingId(posting.getPostingId())
                .title(posting.getTitle())
                .build();
    }
}
