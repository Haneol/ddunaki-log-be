package com.blog.domain.user.service;

import com.blog.domain.user.domain.EmailAndCode;
import com.blog.domain.user.repository.EmailAndCodeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailAndCodeService {
    @Autowired
    private EmailAndCodeRepository emailAndCodeRepository;
    public EmailAndCode emailAndCode;
    public int verify(int authNum) {
        emailAndCode = new EmailAndCode();
//        emailAndCode.setMail(sendTo);
        emailAndCode.setAuthNum(authNum);
        emailAndCodeRepository.save(emailAndCode);
        return authNum;
    }
}
