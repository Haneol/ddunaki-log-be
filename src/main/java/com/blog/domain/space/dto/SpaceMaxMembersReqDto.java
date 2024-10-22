package com.blog.domain.space.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpaceMaxMembersReqDto {
    private Long spaceId;
    private Integer maxMembers;
}
