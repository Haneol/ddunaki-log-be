package com.blog.domain.schedule.api;

import com.blog.domain.schedule.constant.ScheduleResponseMessage;
import com.blog.domain.schedule.dto.ScheduleReqDto;
import com.blog.domain.schedule.dto.ScheduleResDto;
import com.blog.domain.schedule.dto.UpdateScheduleReqDto;
import com.blog.domain.schedule.service.ScheduleService;
import com.blog.global.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("")
    public ResponseEntity<ScheduleResDto> addSchedule(@RequestBody ScheduleReqDto scheduleReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(scheduleService.addSchedule(scheduleReqDto));
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody UpdateScheduleReqDto updateScheduleReqDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(scheduleService.updateSchedule(scheduleId, updateScheduleReqDto));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<MessageDto> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(MessageDto.msg(ScheduleResponseMessage.DELETE_SCHEDULE.getMessage()));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResDto> findSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(scheduleService.findOneSchedule(scheduleId));
    }

    @GetMapping("/{spaceId}/schedules")
    public ResponseEntity<List<ScheduleResDto>> findSchedulesBySpaceId(@PathVariable Long spaceId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(scheduleService.findSchedulesBySpaceId(spaceId));
    }
}
