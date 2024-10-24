package com.blog.domain.user.domain;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Getter
@Component
public class EmailAndCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

//    public String email;
    public int authNum;
    @OneToOne
    @JoinColumn(name = "user_id")
    private com.blog.domain.user.domain.User user;
//    public String getMail() {
//        return email;
//    }

    public int getAuthNum() {
        return authNum;
    }

//    public void setMail(String mail) {
//        this.email = email;
//    }

    public void setAuthNum(int authNum) {
        this.authNum = authNum;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
