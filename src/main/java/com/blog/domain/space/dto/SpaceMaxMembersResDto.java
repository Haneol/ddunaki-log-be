package com.blog.domain.space.dto;

import com.blog.domain.space.domain.Space;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpaceMaxMembersResDto {
    private final Integer maxMembers;
    public static SpaceMaxMembersResDto entityToDto(Space space){
        return SpaceMaxMembersResDto.builder()
                .maxMembers(space.getMaxMembers())
                .build();
    }
}
