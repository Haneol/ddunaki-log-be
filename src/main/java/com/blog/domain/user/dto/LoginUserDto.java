package com.blog.domain.user.dto;

public class LoginUserDto {
    private String email;
    private String pw;
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
