package com.blog.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum UserRole {
        ADMIN,
        MEMBER;
}
