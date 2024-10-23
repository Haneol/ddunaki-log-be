package com.blog.domain.space.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SpaceDateReqDto {
    private LocalDate startDate;
    private LocalDate endDate;
}
