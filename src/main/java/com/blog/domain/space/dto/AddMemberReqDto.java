package com.blog.domain.space.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AddMemberReqDto {
    private Long spaceId;
    private List<Long> memberIds;
}
