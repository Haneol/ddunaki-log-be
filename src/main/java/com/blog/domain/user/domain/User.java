package com.blog.domain.user.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(nullable = false, length=100)
    private String email;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false, length = 50)
    private String nickName;

    private UserRole role;

    @Column(nullable = false)
    private String profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private EmailAndCode emailAndCode;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    public void addRole(UserRole userRole) {
        this.role = role;
    }
    public User setId(long userId) {
        this.userId = userId;
        return this;
    }
    public void setEmailAndCode(EmailAndCode emailAndCode) {
        this.emailAndCode = emailAndCode;
        emailAndCode.setUser(this);
    }
    public User setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getNickName() {
        return nickName;
    }


    public String getProfile() {
        return profile;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public User setPw(String pw) {
        this.pw = pw;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getUserId() {
        return userId;
    }
    public User setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String toString() {
        return "User{" +
                ", fullName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + pw + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

