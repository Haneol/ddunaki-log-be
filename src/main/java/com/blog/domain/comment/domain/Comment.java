package com.blog.domain.comment.domain;


import com.blog.domain.posting.domain.Posting;
import com.blog.domain.user.domain.User;
import com.blog.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id")
    private Posting posting;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Builder
    public Comment(User writer, Posting posting, String content) {
        this.writer = writer;
        this.posting = posting;
        this.content = content;
    }
}
