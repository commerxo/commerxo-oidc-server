package com.commerxo.commerxoopenidserver.domain;

import com.commerxo.commerxoopenidserver.domain.converter.InstantStringConverter;
import com.commerxo.commerxoopenidserver.domain.converter.SimpleGrantedAuthorityStringConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Set;


@Entity
@Table(
        name = "user",
        uniqueConstraints = @UniqueConstraint(
                name = "user_unique",
                columnNames = {"username"}
        )
)
public class User {

    private String uuid;
    private Instant createdAt;
    private String username;
    private String password;
    private String emailId;
    private String phoneNo;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Set<GrantedAuthority> grantedAuthorities;

    @Id
    @UuidGenerator
    @Column(name = "user_id")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Column(name = "created_at")
    @Convert(converter = InstantStringConverter.class)
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email_id")
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name = "phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @CollectionTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "authority")
    @Convert(converter = SimpleGrantedAuthorityStringConverter.class)
    public Set<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Set<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }


    @Column(name = "account_non_expired")
    public boolean isAccountNonExpired() {
        return false;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    @Column(name = "account_non_locked")
    public boolean isAccountNonLocked() {
        return false;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    @Column(name = "credential_non_expired")
    public boolean isCredentialsNonExpired() {
        return false;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Column(name = "enabled")
    public boolean isEnabled() {
        return false;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", grantedAuthorities=" + grantedAuthorities +
                ", isEnabled=" + isEnabled +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                '}';
    }
}