package com.blog.domain.space.domain;

import com.blog.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spaceId;

    @OneToOne
    @JoinColumn(name = "leader", referencedColumnName = "user_id")
    private User leader;

    @Column(nullable = false)
    private String spaceName;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "space_members",
            joinColumns = @JoinColumn(name = "space_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members = new ArrayList<>();


    //null 가능
    private String nationCode;

    //null 가능
    private String cityCode;

    private Integer maxMembers;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd");

    @Builder
    public Space(String spaceName, User leader, LocalDate startDate, LocalDate endDate, String description,
                 List<User> members, String nationCode, String cityCode, Integer maxMembers) {

        this.spaceName = spaceName;
        this.leader = leader;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.members = members != null ? members : new ArrayList<>(); // members가 null이면 빈 리스트로 초기화
        this.nationCode = nationCode;
        this.cityCode = cityCode;
        this.maxMembers = maxMembers;
    }

    public void update(String spaceName, LocalDate startDate, LocalDate endDate, String description,
                       String nationCode, String cityCode, Integer maxMembers) {
        this.spaceName = spaceName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.nationCode = nationCode;
        this.cityCode = cityCode;
        this.maxMembers = maxMembers;
    }

    public void updateSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public void updateDate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void updatedescription(String description) {
        this.description = description;
    }

    public void updateCode(String nationCode, String cityCode) {
        this.nationCode = nationCode;
        this.cityCode = cityCode;
    }

    public void updateMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }

    public void addMember(User newMember) {
        //user 업데이트
        if (!members.contains(newMember))
            members.add(newMember);
    }

    public void deleteMember(User outMember) {
        members.remove(outMember);
    }
}
