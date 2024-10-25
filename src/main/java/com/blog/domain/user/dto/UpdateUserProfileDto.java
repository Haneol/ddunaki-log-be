package com.blog.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserProfileDto {
    private String profile;
}
