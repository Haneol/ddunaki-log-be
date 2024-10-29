package com.blog.domain.schedule.domain;

import com.blog.domain.space.domain.Space;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.PublicKey;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    private String spot;

    private String memo;
    
    @Column(name = "schedule_day")
    private LocalDate day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    private Space space;

    @Builder
    public Schedule(String spot, String memo, LocalDate day, Space space) {
        this.spot = spot;
        this.memo = memo;
        this.day = day;
        this.space = space;
    }

    public void update(String spot, String memo, LocalDate day) {
        this.spot = spot;
        this.memo = memo;
        this.day = day;
    }
}
