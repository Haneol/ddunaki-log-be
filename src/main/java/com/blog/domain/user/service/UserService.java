package com.blog.domain.user.service;

import com.blog.domain.user.config.exception.ExceptionCode;
import com.blog.domain.user.domain.User;
import com.blog.domain.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserService {
    private UserRepository userRepository;
    public boolean isUserHere(String findEmail) {
        if (userRepository.findByEmail(findEmail)!=null) {
            return true;
        }
        return false;
    }

    public boolean updateNickName(String username, String nickname) {
        if (username != null) {
            log.info("username: " + username);
            User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException(ExceptionCode.NO_EMAIL.getMessage()));

            if (user != null) {
                user.setNickName(nickname);
                userRepository.save(user);
                log.info("변경된 닉네임 저장됨");
                return true;
            }
        }
        return false;
    }
}
