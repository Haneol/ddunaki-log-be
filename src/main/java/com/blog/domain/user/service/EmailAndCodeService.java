package com.blog.domain.user.service;

import com.blog.domain.user.domain.EmailAndCode;
import com.blog.domain.user.repository.EmailAndCodeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class EmailAndCodeService {
    @Autowired
    private EmailAndCodeRepository emailAndCodeRepository;
    public boolean verify(String email, int authNum) {

        Optional<EmailAndCode> codeOptional = emailAndCodeRepository.findByEmail(email);
        if (codeOptional.isPresent()) {
            log.info("인증코드 존재함");
            EmailAndCode emailAndCode = codeOptional.get();
            return emailAndCode.getAuthNum() == authNum;
        }
        else {
            log.info("테이블에 값이 없음");
        }
        return false;
    }
}
