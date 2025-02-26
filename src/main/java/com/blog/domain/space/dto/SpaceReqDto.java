package com.blog.domain.space.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceReqDto {
    private String spaceName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String nationCode;
    private String cityCode;
    private Integer maxMembers;
}
