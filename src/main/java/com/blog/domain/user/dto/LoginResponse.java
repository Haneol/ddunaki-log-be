package com.blog.domain.user.dto;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginResponse {
    private String token;
    private long expiresIn;
    private Long userId;
    private String nickName;
    private String email;
    private String profile;

    public String getToken() {
        log.info("getToken");
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }


    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public LoginResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public LoginResponse setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public LoginResponse setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
