package com.blog.domain.space.dto;

import com.blog.domain.space.domain.Space;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpaceDescriptionResDto {

    private final String description;

    public static SpaceDescriptionResDto entityToDto(Space space) {
        return SpaceDescriptionResDto.builder()
                .description(space.getDescription())
                .build();
    }
}
