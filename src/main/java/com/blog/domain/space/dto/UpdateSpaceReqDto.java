package com.blog.domain.space.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UpdateSpaceReqDto {
    private String spaceName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String nationCode;
    private String cityCode;
    private Integer maxMembers;
}
