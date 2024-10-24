package com.blog.domain.posting.service;

import com.blog.domain.posting.dto.PostingReqDto;
import com.blog.domain.posting.dto.PostingResDto;
import com.blog.domain.posting.dto.PostingTitleResDto;
import com.blog.domain.posting.dto.UpdatePostingReqDto;

import java.util.List;

public interface PostingService {
    PostingResDto addPosting(PostingReqDto postingReqDto);

    PostingResDto updatePosting(Long postingId, UpdatePostingReqDto updatePostingReqDto);

    void deletePosting(Long postingId);

    //작성자 닉네임, 게시물 제목, 국가 코드 및 도시 코드 기반 검색
    List<PostingResDto> findAllSearch (String writerNickName, String title, String nationCode, String cityCode );

    PostingTitleResDto findPostingTitle(Long postingId);

    PostingResDto findOnePosting(Long postingId);

    //해당 스페이스의 포스팅 목록
    List<PostingResDto> findAllPostingBySpaceId(Long spaceId);

    //해당 일정에 관련된 포스팅 보기
    List<PostingResDto> findAllPostingByScheduleId(Long scheduleId);

    int getCommentCnt(Long postingId);
}
