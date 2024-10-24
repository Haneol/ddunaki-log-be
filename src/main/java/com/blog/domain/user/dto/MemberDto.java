package com.blog.domain.user.dto;

import com.blog.domain.user.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {

    private final Long userId;
    private final String nickname;
    private final String profile;

    public static MemberDto entityToDto(User user) {
        return MemberDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .profile(user.getProfile())
                .build();
    }
}
