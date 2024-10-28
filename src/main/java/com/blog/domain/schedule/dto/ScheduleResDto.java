package com.blog.domain.schedule.dto;

import com.blog.domain.schedule.domain.Schedule;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ScheduleResDto {

    private final Long scheduleId;
    private final Long spaceId;
    private final String spot;
    private final String memo;
    private final LocalDate day;

    public static ScheduleResDto entityToDto(Schedule schedule) {
        return ScheduleResDto.builder()
                .scheduleId(schedule.getScheduleId())
                .day(schedule.getDay())
                .memo(schedule.getMemo())
                .spot(schedule.getSpot())
                .spaceId(schedule.getSpace().getSpaceId())
                .build();
    }
}
