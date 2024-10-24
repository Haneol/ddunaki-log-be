package com.blog.domain.comment.service;

import com.blog.domain.comment.dao.CommentRepository;
import com.blog.domain.comment.domain.Comment;
import com.blog.domain.comment.dto.CommentReqDto;
import com.blog.domain.comment.dto.CommentResDto;
import com.blog.domain.comment.exception.CommentNotFoundException;
import com.blog.domain.posting.dao.PostingRepository;
import com.blog.domain.posting.domain.Posting;
import com.blog.domain.posting.exception.PostingUserNotFoundException;
import com.blog.domain.schedule.exception.ScheduleUserNotFoundException;
import com.blog.domain.space.domain.Space;
import com.blog.domain.user.dao.UserRepository;
import com.blog.domain.user.domain.User;
import com.blog.global.exception.ForbiddenException;
import com.blog.global.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.blog.domain.comment.constant.CommentExceptionMessage.COMMENT_NOT_FOUND;
import static com.blog.domain.posting.constant.PostingExceptionMessage.POSTING_NOT_FOUND;
import static com.blog.domain.schedule.constant.ScheduleExceptionMessage.NO_PERMISSION;
import static com.blog.domain.schedule.constant.ScheduleExceptionMessage.SCHEDULE_USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostingRepository postingRepository;
    private final SecurityUtils securityUtils;

    private User getLoginUser() {
        String loginUserEmail = securityUtils.getLoginUserEmail();
        return userRepository.findByEmail(loginUserEmail)
                .orElseThrow(() -> new ScheduleUserNotFoundException(SCHEDULE_USER_NOT_FOUND.getMessage()));
    }

    private boolean checkCommentAuthority(Space space, User user, Posting posting) {
        switch (posting.getAccessLevel()) {
            case PUBLIC:
                return true;
            case MEMBER_ONLY:
                return space.getMembers() != null && space.getMembers().contains(user);
            default:
                return false;
        }
    }

    @Override
    public CommentResDto addComment(Long postingId, CommentReqDto commentReqDto) {
        User loginUser = getLoginUser();
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new PostingUserNotFoundException(POSTING_NOT_FOUND.getMessage()));

        // 게시물의 스페이스에 대한 권한 체크
        if (!checkCommentAuthority(posting.getSpace(), loginUser, posting)) {
            throw new ForbiddenException(NO_PERMISSION.getMessage());
        }

        Comment comment = Comment.builder()
                .content(commentReqDto.getContent())
                .writer(loginUser)
                .posting(posting)
                .build();

        posting.addComment(comment);

        commentRepository.save(comment);
        // 댓글 수 반영
        postingRepository.save(posting);

        return CommentResDto.entityToResDto(comment);
    }

    // 댓글 삭제 작성자만 가능
    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND.getMessage()));

        User loginUser = getLoginUser();

        if (!comment.getWriter().equals(loginUser)) {
            throw new ForbiddenException(NO_PERMISSION.getMessage());
        }

        Posting posting = comment.getPosting();
        posting.removeComment(comment);

        commentRepository.delete(comment);
        postingRepository.save(posting);
    }

    @Override
    public CommentResDto findOne(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND.getMessage()));

        return CommentResDto.entityToResDto(comment);
    }

    @Override
    public List<CommentResDto> findCommentByPostId(Long postingId) {
        List<Comment> comments = commentRepository.findByPosting_PostingId(postingId);

        return comments.stream()
                .map(CommentResDto::entityToResDto)
                .collect(Collectors.toList());
    }
}
