package com.blog.domain.space.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteMemberReqDto {
    private Long spaceId;
    private Long memberId;
}
