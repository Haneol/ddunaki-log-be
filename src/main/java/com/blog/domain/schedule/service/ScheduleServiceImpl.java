package com.blog.domain.schedule.service;

import com.blog.domain.schedule.dao.ScheduleRepository;
import com.blog.domain.schedule.domain.Schedule;
import com.blog.domain.schedule.dto.ScheduleReqDto;
import com.blog.domain.schedule.dto.ScheduleResDto;
import com.blog.domain.schedule.dto.UpdateScheduleReqDto;
import com.blog.domain.schedule.exception.ScheduleUserNotFoundException;
import com.blog.domain.space.dao.SpaceRepository;
import com.blog.domain.space.domain.Space;
import com.blog.domain.user.dao.UserRepository;
import com.blog.domain.user.domain.User;
import com.blog.global.exception.ForbiddenException;
import com.blog.global.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.blog.domain.schedule.constant.ScheduleExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final SpaceRepository spaceRepository;
    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;

    private User getLoginUser() {
        String loginUserEmail = securityUtils.getLoginUserEmail();

        return userRepository.findByEmail(loginUserEmail)
                .orElseThrow(() -> new ScheduleUserNotFoundException(SCHEDULE_USER_NOT_FOUND.getMessage()));
    }

    private void checkMemberAuthority(Space space, User user) {
        //유저가 해당 스페이스의 멤버가 아니면 에러
        if (space.getMembers() == null || !space.getMembers().contains(user)) {
            throw new ForbiddenException(NO_PERMISSION.getMessage());
        }
    }

    @Override
    public ScheduleResDto addSchedule(ScheduleReqDto scheduleReqDto) {
        User loginUser = getLoginUser();
        Space space = spaceRepository.findById(scheduleReqDto.getSpaceId())
                .orElseThrow(() -> new ScheduleUserNotFoundException(SCHEDULE_SPACE_NOT_FOUND.getMessage()));

        checkMemberAuthority(space, loginUser);
        Schedule schedule = Schedule.builder()
                .day(scheduleReqDto.getDay())
                .memo(scheduleReqDto.getMemo())
                .spot(scheduleReqDto.getSpot())
                .space(space)
                .build();

        scheduleRepository.save(schedule);
        return ScheduleResDto.entityToDto(schedule);
    }

    @Override
    public ScheduleResDto updateSchedule(UpdateScheduleReqDto updateScheduleReqDto) {
        User loginUser = getLoginUser();
        Space space = spaceRepository.findById(updateScheduleReqDto.getSpaceId())
                .orElseThrow(() -> new ScheduleUserNotFoundException(SCHEDULE_SPACE_NOT_FOUND.getMessage()));

        Schedule schedule = scheduleRepository.findById(updateScheduleReqDto.getScheduleId())
                .orElseThrow(() -> new ScheduleUserNotFoundException(SCHEDULE_NOT_FOUND.getMessage()));

        checkMemberAuthority(space, loginUser);

        schedule.update(updateScheduleReqDto.getSpot(),updateScheduleReqDto.getMemo(),updateScheduleReqDto.getDay());

        scheduleRepository.save(schedule);

        return ScheduleResDto.entityToDto(schedule);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        User loginUser = getLoginUser();

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleUserNotFoundException(SCHEDULE_NOT_FOUND.getMessage()));

        Space space = spaceRepository.findById(schedule.getSpace().getSpaceId())
                .orElseThrow(() -> new ScheduleUserNotFoundException(SCHEDULE_SPACE_NOT_FOUND.getMessage()));

        checkMemberAuthority(space, loginUser);
    }

    @Override
    public ScheduleResDto findOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleUserNotFoundException(SCHEDULE_NOT_FOUND.getMessage()));

        return ScheduleResDto.entityToDto(schedule);
    }

    @Override
    public List<ScheduleResDto> findSchedulesBySpaceId(Long spaceId) {
        //spaceId에 해당하는 모든 스케줄 가져오기
        List<Schedule> schedules = scheduleRepository.findAllBySpaceSpaceId(spaceId);
        return schedules.stream()
                .map(ScheduleResDto::entityToDto)
                .collect(Collectors.toList());
    }
}
