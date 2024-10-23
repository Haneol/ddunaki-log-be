package com.blog.domain.user.domain;

import lombok.Generated;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Component
public class EmailAndCode {
    @Id
    @Generated
    public long id;

    public String email;
    public int authNum;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    public String getMail() {
        return email;
    }

    public int getAuthNum() {
        return authNum;
    }

    public void setMail(String mail) {
        this.email = email;
    }

    public void setAuthNum(int authNum) {
        this.authNum = authNum;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
