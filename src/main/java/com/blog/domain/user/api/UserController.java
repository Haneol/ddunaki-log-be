package com.blog.domain.user.api;

import com.blog.domain.user.dto.UserAliasDto;
import com.blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PutMapping("/changeNickName")
    public ResponseEntity<String> updateUserInfo(@RequestBody UserAliasDto userAliasDto) {
        log.info(userAliasDto);
        log.info("*******닉네임 변경하기 시작*******");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        log.info("username: "+username);
        boolean result = userService.updateNickName(username, userAliasDto.getNickName());
        if (result) {
            return ResponseEntity.ok().body("닉네임 변경 성공");
        }
        return ResponseEntity.badRequest().body("닉네임 변경 실패");
    }
}