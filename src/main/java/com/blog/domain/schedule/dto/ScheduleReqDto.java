package com.blog.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleReqDto {
    private Long spaceId;
    private String spot;
    private String memo;
    private LocalDate day;
}
