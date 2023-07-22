package com.commerxo.commerxoopenidserver.api.user;

import java.io.Serializable;

public class UserRegisterRequest {

    private String username;
    private String password;
    private String emailId;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailId() {
        return emailId;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder implements Serializable{

        private String username;
        private String password;
        private String emailId;

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder emailId(String emailId){
            this.emailId = emailId;
            return this;
        }
        public UserRegisterRequest build(){
            UserRegisterRequest request = new UserRegisterRequest();
            request.username = this.username;
            request.password = this.password;
            request.emailId = this.emailId;
            return request;
        }
    }
}
