package com.blog.domain.posting.service;

import com.blog.domain.posting.dao.PostingRepository;
import com.blog.domain.posting.domain.Posting;
import com.blog.domain.posting.dto.PostingReqDto;
import com.blog.domain.posting.dto.PostingResDto;
import com.blog.domain.posting.dto.PostingTitleResDto;
import com.blog.domain.posting.dto.UpdatePostingReqDto;
import com.blog.domain.posting.exception.PostingNotFoundException;
import com.blog.domain.schedule.dao.ScheduleRepository;
import com.blog.domain.schedule.domain.Schedule;
import com.blog.domain.schedule.exception.ScheduleNotFoundException;
import com.blog.domain.schedule.exception.ScheduleUserNotFoundException;
import com.blog.domain.space.dao.SpaceRepository;
import com.blog.domain.space.domain.Space;
import com.blog.domain.space.exception.SpaceNotFoundException;
import com.blog.domain.user.domain.User;
import com.blog.domain.user.repository.UserRepository;
import com.blog.global.exception.ForbiddenException;
import com.blog.global.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.blog.domain.posting.constant.PostingExceptionMessage.POSTING_NOT_FOUND;
import static com.blog.domain.schedule.constant.ScheduleExceptionMessage.*;
import static com.blog.domain.space.constant.SpaceExceptionMessage.SPACE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private final SpaceRepository spaceRepository;
    private final UserRepository userRepository;
    private final PostingRepository postingRepository;
    private final SecurityUtils securityUtils;
    private final ScheduleRepository scheduleRepository;

    private User getLoginUser() {
        String loginUserEmail = securityUtils.getLoginUserEmail();

        return userRepository.findByEmail(loginUserEmail)
                .orElseThrow(() -> new ScheduleUserNotFoundException(SCHEDULE_USER_NOT_FOUND.getMessage()));
    }

    private void checkMemberAuthority(Space space, User user) {
        //유저가 해당 스페이스의 멤버가 아니면 에러
        if (space.getMembers() == null || !space.getMembers().contains(user)) {
            throw new ForbiddenException(NO_PERMISSION.getMessage());
        }
    }

    private boolean checkPostingAuthority(Space space, User user, Posting posting) {
        switch (posting.getAccessLevel()) {
            case PUBLIC:
                return true;  // PUBLIC이면 누구나 접근 가능
            case MEMBER_ONLY:
                // MEMBER_ONLY일 때 유저가 해당 스페이스의 멤버인지 확인
                return space.getMembers() != null && space.getMembers().contains(user);
            default:
                return false;  // 그 외 접근 권한 없음
        }
    }


    //포스팅 작성은 멤버만
    @Override
    public PostingResDto addPosting(PostingReqDto postingReqDto) {

        User writer = getLoginUser();

        Space space = spaceRepository.findById(postingReqDto.getSpaceId())
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        checkMemberAuthority(space, writer);

        Schedule schedule = scheduleRepository.findById(postingReqDto.getScheduleId())
                .orElseThrow(() -> new ScheduleUserNotFoundException(SCHEDULE_NOT_FOUND.getMessage()));

        Posting posting = Posting.builder()
                .writer(writer)
                .space(space)
                .schedule(schedule)
                .title(postingReqDto.getTitle())
                .content(postingReqDto.getContent())
                .accessLevel(postingReqDto.getAccessLevel())
                .mainImgUrl(postingReqDto.getMainImgUrl())
                .commentCnt(0)
                .build();

        postingRepository.save(posting);

        return PostingResDto.entityToResDto(posting);
    }


    @Override
    public PostingResDto updatePosting(Long postingId, UpdatePostingReqDto updatePostingReqDto) {
        User writer = getLoginUser();
        Space space = spaceRepository.findById(updatePostingReqDto.getSpaceId())
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        Schedule schedule = scheduleRepository.findById(updatePostingReqDto.getScheduleId())
                .orElseThrow(() -> new ScheduleNotFoundException(SCHEDULE_NOT_FOUND.getMessage()));

        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new PostingNotFoundException(POSTING_NOT_FOUND.getMessage()));

        //작성자만 업데이트 가능
        if (!posting.getWriter().equals(writer)) {
            throw new ForbiddenException(NO_PERMISSION.getMessage());
        }

        posting.update(updatePostingReqDto.getTitle(), updatePostingReqDto.getContent(),
                updatePostingReqDto.getMainImgUrl(), updatePostingReqDto.getAccessLevel());

        postingRepository.save(posting);
        return PostingResDto.entityToResDto(posting);
    }

    @Override
    public Page<PostingResDto> findAllSearch(String writerNickName, String title, String nationCode, String cityCode, int page) {
        User loginUser = getLoginUser();
        // 20개씩 페이지네이션
        Pageable pageable = PageRequest.of(page, 20);

        // 모든 조건이 null이면 전체 리스트 반환
        if (writerNickName == null && title == null && nationCode == null && cityCode == null) {
            return new PageImpl<>(
                    postingRepository.findAll(pageable).stream()
                            .filter(posting -> checkPostingAuthority(posting.getSpace(), loginUser, posting))  // 권한 확인
                            .map(PostingResDto::entityToResDto)
                            .collect(Collectors.toList()), pageable, postingRepository.count());
        }


        // 조건에 맞는 게시물을 검색 (페이지네이션 적용)
        Page<Posting> postings = postingRepository.findByWriter_NickNameContainingAndTitleContainingAndSpace_NationCodeContainingAndSpace_CityCodeContaining(
                writerNickName != null ? writerNickName : "",
                title != null ? title : "",
                nationCode != null ? nationCode : "",
                cityCode != null ? cityCode : "",
                pageable
        );

        // 권한이 있는 게시물만 필터링 후 페이지로 반환
        List<PostingResDto> filteredPostings = postings.stream()
                .filter(posting -> checkPostingAuthority(posting.getSpace(), loginUser, posting))  // 권한이 있는 게시물만 필터링
                .map(PostingResDto::entityToResDto)
                .collect(Collectors.toList());

        return new PageImpl<>(filteredPostings, pageable, postings.getTotalElements());
    }

    @Override
    public PostingTitleResDto findPostingTitle(Long postingId) {

        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new PostingNotFoundException(POSTING_NOT_FOUND.getMessage()));

        return PostingTitleResDto.entityToResDto(posting);
    }

    // 스페이스에 속한 전체 포스팅 조회
    @Override
    public List<PostingResDto> findAllPostingBySpaceId(Long spaceId) {
        User loginUser = getLoginUser();

        List<Posting> postings = postingRepository.findAllBySpace_SpaceId(spaceId);

        // 권한이 있는 게시물만 필터링
        return postings.stream()
                .filter(posting -> checkPostingAuthority(posting.getSpace(), loginUser, posting))
                .map(PostingResDto::entityToResDto)
                .collect(Collectors.toList());
    }

    // 특정 포스팅 조회
    @Override
    public PostingResDto findOnePosting(Long postingId) {
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new PostingNotFoundException(POSTING_NOT_FOUND.getMessage()));

        User loginUser = getLoginUser();
        Space space = posting.getSpace();

        checkPostingAuthority(space, loginUser, posting);

        return PostingResDto.entityToResDto(posting);
    }

    // 포스팅 삭제
    @Override
    public void deletePosting(Long postingId) {
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new PostingNotFoundException(POSTING_NOT_FOUND.getMessage()));

        User loginUser = getLoginUser();

        // 작성자만 삭제 가능
        if (!posting.getWriter().equals(loginUser)) {
            throw new ForbiddenException(NO_PERMISSION.getMessage());
        }

        postingRepository.delete(posting);
    }

    // 일정에 맞는 모든 포스팅 조회
    @Override
    public List<PostingResDto> findAllPostingByScheduleId(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException(SCHEDULE_NOT_FOUND.getMessage()));

        List<Posting> postings = postingRepository.findAllBySchedule(schedule);

        return postings.stream()
                .map(PostingResDto::entityToResDto)
                .collect(Collectors.toList());
    }

    // 댓글 수 조회 로직
    @Override
    public int getCommentCnt(Long postingId) {
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new PostingNotFoundException(POSTING_NOT_FOUND.getMessage()));

        return posting.getCommentCnt();
    }
}