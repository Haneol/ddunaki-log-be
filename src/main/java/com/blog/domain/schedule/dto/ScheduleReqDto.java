package com.blog.domain.schedule.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ScheduleReqDto {
    private Long spaceId;
    private String spot;
    private String memo;
    private LocalDate day;
}
