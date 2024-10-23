package com.blog.domain.user.service;

import com.blog.domain.user.config.exception.BusinessLogicException;
import com.blog.domain.user.config.exception.ExceptionCode;

import com.blog.domain.user.domain.User;
import com.blog.domain.user.repository.EmailAndCodeRepository;
import com.blog.domain.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.blog.domain.user.config.exception.BusinessLogicException;
import com.blog.domain.user.config.exception.ExceptionCode;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Log4j2
@Service
public class MailServiceImpl implements MailService{
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;
    private int authNum;
    private static final String AUTH_CODE_PREFIX = "AuthCode ";

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    public MailServiceImpl(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }
    @Override
    public void createNumber() {
        int n = (int)(Math.random() * (90000)) + 100000;
        authNum = n;
    }

    @Override
    public MimeMessage createMail(String email) {
        createNumber();
        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setFrom(username);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "안녕하세요. 여행 일정 공유 블로그 플랫폼 떠나기록 입니다." + "</h3>";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + authNum + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8","html");
            log.info("메일 내용 생성됨");
        } catch (MessagingException e) {
            log.info("error"+e);
        }
        return message;
    }

    @Override
    public int sendMail(String email) {
        checkDuplicatedEMail(email);
        MimeMessage message = createMail(email);

        new Thread(()->{
            mailSender.send(message);
            System.out.println("Mail sent successfully...");

        }).start();
        return authNum;
    }

    private void checkDuplicatedEMail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.info("already exist.");
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }

}
