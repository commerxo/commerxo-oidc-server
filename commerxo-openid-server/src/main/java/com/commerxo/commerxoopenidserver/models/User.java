package com.commerxo.commerxoopenidserver.models;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.Instant;

public class User {

    private String id;
    private Instant createdAt;
    private Instant updatedAt;
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailId;
    private String phoneNo;
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean emailIdVerified;
    private boolean phoneVerified;

    public String getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public boolean isEmailIdVerified() {
        return emailIdVerified;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public static Builder withId(String id){
        return new Builder(id);
    }

    public static Builder from(User user){
        if(user == null)
            throw new IllegalArgumentException("user can't be null!");
        return new Builder(user);
    }

    public static class Builder implements Serializable{

        private String id;
        private Instant createdAt;
        private Instant updatedAt;
        private String username;
        private String password;
        private String firstName;
        private String middleName;
        private String lastName;
        private String emailId;
        private String phoneNo;
        private boolean enabled;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean emailIdVerified;
        private boolean phoneVerified;

        public Builder(){}

        public Builder(String id){
            if(!StringUtils.hasText(id))
                throw new IllegalArgumentException("Id can't be empty!");
            this.id = id;
        }

        public Builder(User user){
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.emailId = user.getEmailId();
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder createdAt(Instant createdAt){
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Instant updatedAt){
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(String middleName){
            this.middleName = middleName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
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

        public Builder emailVerified(boolean emailIdVerified){
            this.emailIdVerified = emailIdVerified;
            return this;
        }


        public Builder enabled(boolean enabled){
            this.enabled = enabled;
            return this;
        }

        public Builder accountExpired(boolean accountExpired){
            this.accountExpired = accountExpired;
            return this;
        }

        public Builder accountLocked(boolean accountLocked){
            this.accountLocked = accountLocked;
            return this;
        }

        public Builder credentialsExpired(boolean credentialsExpired){
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public User build(){
            User user = new User();
            user.id = this.id;
            user.createdAt = this.createdAt;
            user.updatedAt = this.updatedAt;
            user.username = this.username;
            user.firstName = this.firstName;
            user.middleName = this.middleName;
            user.lastName = this.lastName;
            user.password = this.password;
            user.emailId =  this.emailId;
            user.enabled = this.enabled;
            user.accountExpired = this.accountExpired;
            user.accountLocked = this.accountLocked;
            user.emailIdVerified = this.emailIdVerified;
            user.credentialsExpired = this.credentialsExpired;
            return user;
        }
    }

}
