package com.blog.domain.user.domain;

import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.stream.Stream;

@Getter
public enum UserRole {
        ADMIN,
        MEMBER;
}
