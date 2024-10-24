package com.blog.domain.user.api;

import com.blog.domain.user.config.exception.ExceptionCode;
import com.blog.domain.user.dto.UpdateUserNickNameDto;
import com.blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PutMapping("/user")
    public ResponseEntity<?> updateUserInfo(@RequestBody UpdateUserNickNameDto updateUserNickNameDto) {
        log.info("*******닉네임 변경하기 시작*******");
        try {
            userService.updateNickName(updateUserNickNameDto);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body("닉네임 변경 성공");

    }
}