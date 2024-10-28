package com.blog.domain.follow.dao;

import com.blog.domain.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow,Long> {
}
