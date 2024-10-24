package com.blog.domain.posting.constant;

import com.blog.global.exception.NotFoundException;

public enum PostingAccessLevel  {
    PUBLIC("전체 공개"),
    MEMBER_ONLY("멤버 공개");

    private final String value;

    PostingAccessLevel (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PostingAccessLevel  getAccessLevel(String value) {
        for (PostingAccessLevel  type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new NotFoundException();
    }
}
