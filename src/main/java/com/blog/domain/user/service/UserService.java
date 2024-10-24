package com.blog.domain.user.service;

import com.blog.domain.user.config.exception.ExceptionCode;
import com.blog.domain.user.domain.User;
import com.blog.domain.user.dto.UpdateUserNickNameDto;
import com.blog.domain.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public void updateNickName(UpdateUserNickNameDto nickNameDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userRepository.findByEmail(username).orElseThrow(()->new RuntimeException(ExceptionCode.NO_EMAIL.getMessage()));

            if (user != null) {
                user.setNickName(nickNameDto.getNickName());
                userRepository.save(user);
            }
            else {
                throw new RuntimeException();
            }
        }
    }
}