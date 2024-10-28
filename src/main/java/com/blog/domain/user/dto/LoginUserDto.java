package com.blog.domain.user.dto;

import com.blog.domain.user.domain.UserRole;
import lombok.Getter;

@Getter
public class LoginUserDto {
    private String email;
    private Long userId;
    private String nickName;
    private String pw;
    private String profile;
    private UserRole role;

    public LoginUserDto setEmail(String email) {
        this.email = email;
        return this;
    }
    public LoginUserDto setPassword(String pw) {
        this.pw = pw;
        return this;
    }
    @Override
    public String toString() {
        return "LoginUserDto{" +
                "email='" + email + '\'' +
                ", password='" + pw + '\'' +
                '}';
    }
}