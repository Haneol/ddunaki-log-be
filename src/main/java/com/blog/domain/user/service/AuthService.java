package com.blog.domain.user.service;


import com.blog.domain.user.domain.User;
import com.blog.domain.user.dto.LoginUserDto;
import com.blog.domain.user.dto.RegisterUserDto;
import com.blog.domain.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
        this.userService = userService;
    }


    public User signup(RegisterUserDto input) {
        User user = new User()
                .setNickName(input.getNickName())
                .setEmail(input.getEmail())
                .setPw(passwordEncoder.encode(input.getPw()));
        log.info("sign up finish");
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        log.info("인증실행");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPw()
                )
        );
        log.info("인증완료");
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

}
