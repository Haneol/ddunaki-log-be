package com.blog.domain.user.service;

import javax.mail.internet.MimeMessage;

public interface MailService {
    void createNumber();
    MimeMessage createMail(String email);
    int sendMail(String email);
}
