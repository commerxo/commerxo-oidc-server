//package com.commerxo.commerxoopenidserver.domain;
//
//import com.commerxo.commerxoopenidserver.domain.converter.SimpleGrantedAuthorityStringConverter;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import jakarta.persistence.*;
//import org.hibernate.annotations.UuidGenerator;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Set;
//
//@Entity
//@Table(
//        name = "user_group",
//        uniqueConstraints = @UniqueConstraint(
//                name = "user_group_unique",
//                columnNames = {"group_name"}
//        )
//)
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
//public class UserGroup extends Auditable<String>{
//
//    private String id;
//    private String groupName;
//    private String description;
//    private boolean enabled;
//    private Set<User> users;
//    private Set<GrantedAuthority> groupAuthorities;
//
//    @Id
//    @UuidGenerator
//    @Column(name = "group_id")
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Column(name = "group_name")
//    public String getGroupName() {
//        return groupName;
//    }
//
//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }
//
//    @Column(name = "description")
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Column(name = "enabled")
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    @CollectionTable(
//            name = "group_authority",
//            joinColumns = @JoinColumn(name = "group_id")
//    )
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Column(name = "authority")
//    @Convert(converter = SimpleGrantedAuthorityStringConverter.class)
//    public Set<GrantedAuthority> getGroupAuthorities() {
//        return groupAuthorities;
//    }
//
//    public void setGroupAuthorities(Set<GrantedAuthority> groupAuthorities) {
//        this.groupAuthorities = groupAuthorities;
//    }
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            mappedBy = "userGroups"
//    )
//    @JsonBackReference
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//}
