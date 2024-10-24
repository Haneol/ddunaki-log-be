package com.blog.domain.user.api;

import com.blog.domain.user.domain.User;
import com.blog.domain.user.dto.LoginResponse;
import com.blog.domain.user.dto.LoginUserDto;
import com.blog.domain.user.dto.RegisterUserDto;
import com.blog.domain.user.service.AuthService;
import com.blog.domain.user.service.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api")
@RestController
@Log4j2
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto) {
        try {
            User registeredUser = authService.signup(registerUserDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authService.authenticate(loginUserDto);
        log.info("실행됨?");
        String jwtToken =jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION,"Bearer " + jwtToken)
                .header("Expires-In", String.valueOf(jwtService.getExpirationTime()))
                .body("Login successful");
    }

    @PostMapping("/user")
    public ResponseEntity<String>  userWithdrawl(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean result = authService.withdrawl(username);
        if (result) {
            log.info("bye");
            return ResponseEntity.ok("회원탈퇴 성공");
        } else {
            model.addAttribute("failed withdrawl", "회원탈퇴 실패");
            return ResponseEntity.badRequest().body("회원탈퇴 실패");
        }
    }
}
