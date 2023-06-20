package com.commerxo.commerxoopenidserver.api.user;

import com.commerxo.commerxoopenidserver.domain.User;

import java.time.Instant;

public class UserCreationRequest {

    private String username;
    private String password;
    private String emailId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public static User mapToEntity(UserCreationRequest request){
        User user = new User();
        user.setCreatedAt(Instant.now());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmailId(request.getEmailId());
        return user;
    }
}
