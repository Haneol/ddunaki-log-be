package com.blog.domain.space.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpaceCodeReqDto {
    private Long spaceId;
    private String nationCode;
    private String cityCode;
}
