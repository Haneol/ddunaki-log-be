package com.blog.domain.posting.dao;

import com.blog.domain.posting.domain.Posting;
import com.blog.domain.schedule.domain.Schedule;
import com.blog.domain.space.domain.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    // 작성자 닉네임, 게시물 제목, 국가 코드 및 도시 코드 기반 검색
    List<Posting> findByWriter_NickNameAndTitleContainingAndSpace_NationCodeAndSpace_CityCode(
            String writerNickName,
            String title,
            String nationCode,
            String cityCode
    );

    List<Posting> findAllBySpace_SpaceId(Long spaceId);

    // Schedule에 속한 모든 Posting을 찾는 메서드
    List<Posting> findAllBySchedule(Schedule schedule);

    // Space와 Schedule에 속한 모든 Posting을 찾는 메서드
    List<Posting> findAllBySpaceAndSchedule(Space space, Schedule schedule);
}
