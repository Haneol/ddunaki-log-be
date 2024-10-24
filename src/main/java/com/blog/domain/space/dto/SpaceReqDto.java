package com.blog.domain.space.dto;

import com.blog.domain.user.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class SpaceReqDto {

    private Long leaderId;
    private String spaceName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
//    private List<Long> memberIds;
    private String nationCode;
    private String cityCode;
    private Integer maxMembers;
}
