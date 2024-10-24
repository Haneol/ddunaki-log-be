package com.blog.domain.space.dto;

import com.blog.domain.space.domain.Space;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SpaceDateResDto {

    private final Long spaceId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public static SpaceDateResDto entityToDto(Space space){
        return SpaceDateResDto.builder()
                .startDate(space.getStartDate())
                .endDate(space.getEndDate())
                .build();
    }
}
