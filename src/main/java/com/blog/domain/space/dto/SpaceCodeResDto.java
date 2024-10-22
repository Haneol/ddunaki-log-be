package com.blog.domain.space.dto;

import com.blog.domain.space.domain.Space;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpaceCodeResDto {
    private final Integer nationCode;
    private final Integer cityCode;

    public static SpaceCodeResDto entityToDto(Space space){
        return SpaceCodeResDto.builder()
                .nationCode(space.getNationCode())
                .cityCode(space.getCityCode())
                .build();
    }
}
