package com.blog.domain.user.dto;

public class MailInfo {
    private String sendTo;

    private MailInfo(String sendTo) {
        this.sendTo = sendTo;

    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

}
