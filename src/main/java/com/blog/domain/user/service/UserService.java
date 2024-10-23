package com.blog.domain.user.service;

import com.blog.domain.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
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
}
