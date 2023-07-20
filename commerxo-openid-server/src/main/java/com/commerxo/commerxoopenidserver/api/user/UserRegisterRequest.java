package com.commerxo.commerxoopenidserver.api.user;

import com.commerxo.commerxoopenidserver.models.User;
import org.springframework.util.StringUtils;

public class UserRegisterRequest {

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

    public static User mapToUser(UserRegisterRequest registerRequest){
        User user = new User();

        return user;
    }

    private static void validateRequest(UserRegisterRequest request){
        if(!StringUtils.hasText(request.getUsername()))
            throw new IllegalArgumentException();
        if(!StringUtils.hasText(request.getPassword()))
            throw new IllegalArgumentException("");
    }


}
