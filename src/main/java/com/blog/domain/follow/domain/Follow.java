package com.blog.domain.follow.domain;

import com.blog.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer followingId;
    private Integer followerId;

    @Builder
    public Follow(User user, Integer followingId, Integer followerId) {
        this.user = user;
        this.followingId = followingId;
        this.followerId = followerId;
    }
}
