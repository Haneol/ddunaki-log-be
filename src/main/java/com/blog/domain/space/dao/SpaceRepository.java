package com.blog.domain.space.dao;

import com.blog.domain.space.domain.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    List<Space> findByMembers_UserId(Long userId);
}
