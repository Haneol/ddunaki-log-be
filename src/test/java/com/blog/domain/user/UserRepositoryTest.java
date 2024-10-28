package com.blog.domain.user;


import com.blog.domain.user.domain.User;

import com.blog.domain.user.domain.UserRole;
import com.blog.domain.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Log4j2
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Test
    public void insertUser() {
        User user = new User().setNickName("churu").setEmail("never@naver.com").setPw(passwordEncoder.encode("1234"));
        userRepository.save(user);
    }

}
