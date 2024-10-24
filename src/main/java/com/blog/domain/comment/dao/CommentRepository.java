package com.blog.domain.comment.dao;

import com.blog.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPosting_PostingId(Long postingId);
}
