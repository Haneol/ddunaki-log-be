package com.blog.domain.space.dto;

import com.blog.domain.space.domain.Space;
import com.blog.domain.user.domain.User;
import com.blog.domain.user.dto.MemberDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class SpaceMembersResDto {
    private final List<MemberDto> members;

    public static SpaceMembersResDto entityToDto(Space space){
        List<MemberDto> memberDtos = space.getMembers().stream()
                .map(MemberDto::entityToDto)
                .collect(Collectors.toList());

        return SpaceMembersResDto.builder()
                .members(memberDtos)
                .build();
    }
}
