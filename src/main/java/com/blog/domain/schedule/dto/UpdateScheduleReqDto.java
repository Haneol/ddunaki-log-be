package com.blog.domain.schedule.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UpdateScheduleReqDto {
    private Long spaceId;
    private Long scheduleId;
    private String spot;
    private String memo;
    private LocalDate day;
}
