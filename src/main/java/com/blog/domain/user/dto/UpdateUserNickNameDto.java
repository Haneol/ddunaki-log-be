package com.blog.domain.user.dto;

import lombok.Data;

@Data
public class UpdateUserNickNameDto {
    private String nickName;
    public UpdateUserNickNameDto(String nickName) {
        this.nickName = nickName;
    }
}
