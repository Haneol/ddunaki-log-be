package com.blog.domain.user.dto;

public class RegisterUserDto {
    private String email;
    private String pw;
    private String nickName;
    private String profile;
    public String getEmail() {
        return email;
    }

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPw() {
        return pw;
    }

    public RegisterUserDto setPassword(String pw) {
        this.pw = pw;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public RegisterUserDto setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    @Override
    public String toString() {
        return "RegisterUserDto{" +
                "email='" + email + '\'' +
                ", password='" + pw + '\'' +
                ", fullName='" + nickName + '\'' +
                '}';
    }
}
