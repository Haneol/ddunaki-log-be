package com.blog.domain.user.dto;

import com.blog.domain.user.domain.UserRole;

public class LoginUserDto {
    private String email;
    private String pw;
    private String profile;
    private UserRole role;
    public String getEmail() {
        return email;
    }

    public LoginUserDto setEmail(String email) {
        this.email = email;
        return this;
    }


    public String getPw() {
        return pw;
    }

    public String getProfile() {
        return profile;
    }

    public UserRole getRole() {
        return role;
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