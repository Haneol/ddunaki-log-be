package com.blog.domain.space.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpaceCodeReqDto {
    private final Long spaceId;
    private final Integer nationCode;
    private final Integer cityCode;
}
