package com.blog.domain.posting.domain;

import com.blog.domain.comment.domain.Comment;
import com.blog.domain.posting.constant.PostingAccessLevel;
import com.blog.domain.schedule.domain.Schedule;
import com.blog.domain.space.domain.Space;
import com.blog.domain.user.domain.User;
import com.blog.global.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    @OrderBy("commentId desc")
    @JsonIgnoreProperties({"posting"})
    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    private PostingAccessLevel accessLevel;

    private int commentCnt;

    private String mainImgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    private Space space;

    @Builder
    public Posting(String title, String content, String mainImgUrl, Integer commentCnt,
                   PostingAccessLevel accessLevel, User writer, List<Comment> comments, Schedule schedule, Space space) {
        this.title = title;
        this.content = content;
        this.mainImgUrl = mainImgUrl;
        this.accessLevel = accessLevel;
        this.writer = writer;
        this.comments = comments != null ? comments : new ArrayList<>();
        this.schedule = schedule;
        this.space = space;
        this.commentCnt = commentCnt != null ? commentCnt : 0;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        incrementCommentCount();
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        decrementCommentCount();
    }

    public void incrementCommentCount() {
        this.commentCnt++;
    }

    public void decrementCommentCount() {
        if (this.commentCnt > 0) {
            this.commentCnt--;
        }
    }

    public void update(String title, String content, String mainImgUrl, PostingAccessLevel accessLevel) {
        this.title = title;
        this.content = content;
        this.mainImgUrl = mainImgUrl;
        this.accessLevel = accessLevel;
    }

    public void updateSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

}
