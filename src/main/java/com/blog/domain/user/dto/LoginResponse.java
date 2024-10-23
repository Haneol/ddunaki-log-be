package com.blog.domain.user.dto;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginResponse {
    private String token;
    private long expiresIn;

    public String getToken() {
        log.info("getToken");
        return token;
    }

    public LoginResponse setToken(String token) {
        log.info("setToken");
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }


    public LoginResponse setExpiresIn(long expiresIn) {
        log.info("setExpiresIn");
        this.expiresIn = expiresIn;
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
