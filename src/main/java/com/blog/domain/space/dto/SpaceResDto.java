package com.blog.domain.space.dto;

import com.blog.domain.space.domain.Space;
import com.blog.domain.user.dto.MemberDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class SpaceResDto {

    private final Long spaceId;
    private final Long leaderId;
    private final String spaceName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;
    private final List<MemberDto> members;
    private final Integer nationCode;
    private final Integer cityCode;
    private final Integer maxMembers;

    public static SpaceResDto entityToDto(Space space) {
        List<MemberDto> memberDtos = space.getMembers().stream()
                .map(MemberDto::entityToDto)
                .collect(Collectors.toList());

        return SpaceResDto.builder()
                .spaceId(space.getSpaceId())
                .leaderId(space.getLeader().getUserId())
                .spaceName(space.getSpaceName())
                .startDate(space.getStartDate())
                .endDate(space.getEndDate())
                .description(space.getDescription())
                .members(memberDtos)
                .nationCode(space.getNationCode())
                .cityCode(space.getCityCode())
                .maxMembers(space.getMaxMembers())
                .build();
    }
}
