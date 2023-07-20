//package com.commerxo.commerxoopenidserver.domain;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import jakarta.persistence.*;
//import org.hibernate.annotations.UuidGenerator;
//
//import java.util.Date;
//import java.util.Set;
//
//
//@Entity
//@Table(
//        name = "user",
//        uniqueConstraints = @UniqueConstraint(
//                name = "user_unique",
//                columnNames = {"username"}
//        )
//)
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
//public class User {
//
//    private String id;
//    private Date createdAt;
//    private String username;
//    private String password;
//    private String firstName;
//    private String middleName;
//    private String lastName;
//    private String emailId;
//    private String phoneNo;
//    private Set<Role> roles;
//    private Set<UserGroup> userGroups;
//    private boolean enabled =  false;
//    private boolean accountNonExpired =  false;
//    private boolean accountNonLocked =  false;
//    private boolean credentialsNonExpired =  false;
//
//    @Id
//    @UuidGenerator
//    @Column(name = "user_id")
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Column(name = "created_at")
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    @Column(name = "username", unique = true, nullable = false)
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Column(name = "password", nullable = false)
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Column(name = "first_name", length = 30)
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    @Column(name = "middle_name", length = 30)
//    public String getMiddleName() {
//        return middleName;
//    }
//
//    public void setMiddleName(String middleName) {
//        this.middleName = middleName;
//    }
//
//    @Column(name = "last_name", length = 30)
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Column(name = "email_id", nullable = false)
//    public String getEmailId() {
//        return emailId;
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
//    @Column(name = "phone_no")
//    public String getPhoneNo() {
//        return phoneNo;
//    }
//
//    public void setPhoneNo(String phoneNo) {
//        this.phoneNo = phoneNo;
//    }
//
//    @ManyToMany(
//            fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.ALL
//            })
//    @JoinTable(
//            name = "user_role",
//            joinColumns = {
//                    @JoinColumn(name = "user_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "role_id")
//            }
//    )
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//
//    @ManyToMany(
//            fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.ALL
//            })
//    @JoinTable(
//            name = "user_auth_group",
//            joinColumns = {
//                    @JoinColumn(name = "user_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "group_id")
//            }
//    )
//    @JsonManagedReference
//    public Set<UserGroup> getUserGroups() {
//        return userGroups;
//    }
//
//    public void setUserGroups(Set<UserGroup> userGroups) {
//        this.userGroups = userGroups;
//    }
//
//    @Column(name = "account_non_expired",nullable = false)
//    public boolean isAccountNonExpired() {
//        return accountNonExpired;
//    }
//
//    public void setAccountNonExpired(boolean accountNonExpired) {
//        this.accountNonExpired = accountNonExpired;
//    }
//
//    @Column(name = "account_non_locked", nullable = false)
//    public boolean isAccountNonLocked() {
//        return accountNonLocked;
//    }
//
//    public void setAccountNonLocked(boolean accountNonLocked) {
//        this.accountNonLocked = accountNonLocked;
//    }
//
//    @Column(name = "credential_non_expired", nullable = false)
//    public boolean isCredentialsNonExpired() {
//        return credentialsNonExpired;
//    }
//
//    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
//        this.credentialsNonExpired = credentialsNonExpired;
//    }
//
//    @Column(name = "enabled", nullable = false)
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//
//}