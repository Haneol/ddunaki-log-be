package com.blog.domain.space.dto;

import com.blog.domain.space.domain.Space;
import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
@Builder
public class SpaceNameResDto {

    private final String spaceName;
    public static SpaceNameResDto entityToDto(Space space){
        return SpaceNameResDto.builder()
                .spaceName(space.getSpaceName())
                .build();
    }
}
