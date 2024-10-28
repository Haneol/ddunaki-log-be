package com.blog.domain.schedule.service;

import com.blog.domain.schedule.dto.ScheduleReqDto;
import com.blog.domain.schedule.dto.ScheduleResDto;
import com.blog.domain.schedule.dto.UpdateScheduleReqDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResDto addSchedule(ScheduleReqDto scheduleReqDto);

    ScheduleResDto updateSchedule(Long scheduleId, UpdateScheduleReqDto updateScheduleReqDto);

    void deleteSchedule(Long scheduleId);

    ScheduleResDto findOneSchedule(Long scheduleId);

    List<ScheduleResDto> findSchedulesBySpaceId(Long spaceId);
}
