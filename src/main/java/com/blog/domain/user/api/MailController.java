package com.blog.domain.user.api;

import com.blog.domain.user.domain.EmailAndCode;
import com.blog.domain.user.repository.EmailAndCodeRepository;
import com.blog.domain.user.service.EmailAndCodeService;
import com.blog.domain.user.service.MailService;
import com.blog.domain.user.service.MailServiceImpl;
import com.blog.domain.user.domain.EmailAndCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/user")
public class MailController {
    private final MailService mailService;
    private final EmailAndCodeService emailAndCodeService;
    private EmailAndCodeRepository emailAndCodeRepository;
    private int count;
    @Autowired
    public MailController(MailServiceImpl mailService, EmailAndCode emailAndCode, EmailAndCodeService emailAndCodeService, EmailAndCodeRepository emailAndCodeRepository) {
        this.mailService = mailService;
        this.emailAndCodeService = emailAndCodeService;
        this.emailAndCodeRepository = emailAndCodeRepository;
    }
    private int number;

    @PostMapping("/send-email-code")
    public ResponseEntity<String> sendMail(@RequestParam("sendTo") String sendTo) {
        log.info("메일 보내기 시도");
        if (sendTo != null && !sendTo.trim().isEmpty()) {
            int authNum = mailService.sendMail(sendTo);
            EmailAndCode emailAndCode = new EmailAndCode();
            emailAndCode.setMail(sendTo);
            emailAndCode.setAuthNum(authNum);
            emailAndCodeRepository.save(emailAndCode);
            if(emailAndCode.getAuthNum()!=0) {
                log.info(emailAndCode.getAuthNum()+" auth number 값임");
                log.info("email과 code 저장됨");
                log.info(emailAndCodeRepository.count());
                count++;
            }
            return ResponseEntity.ok("Mail Sent successfully. Please check your mail.");
        } else {
            return ResponseEntity.badRequest().body("Please send username");
        }
    }

    @GetMapping("/check-email-code")
    public ResponseEntity<?> mailCheck(@RequestParam String mail, @RequestParam int authNum) {
        log.info("이메일 인증코드 검증 중..");
        if (count>0){
            log.info("이메일 인증 성공");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            log.info("이메일 인증 실패");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
