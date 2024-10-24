package com.blog.domain.user.repository;

import com.blog.domain.user.domain.EmailAndCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAndCodeRepository extends JpaRepository<EmailAndCode, String> {

}
