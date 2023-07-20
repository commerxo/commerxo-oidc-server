//package com.commerxo.commerxoopenidserver.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import org.hibernate.annotations.UuidGenerator;
//
//import java.util.List;
//
//@Entity
//@Table(
//        name = "role",
//        uniqueConstraints = {
//                @UniqueConstraint(
//                        name = "role_unique",
//                        columnNames = {"role_name"}
//                )
//        }
//)
//public class Role extends Auditable<String>{
//
//    private String id;
//    private String name;
//    private String description;
//    private boolean active;
//    private List<User> users;
//
//    @Id
//    @UuidGenerator
//    @Column(name = "role_id")
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Column(name = "description", length = 100)
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Column(name = "active")
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//
//    @Column(name = "role_name", length = 30)
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            mappedBy = "roles"
//    )
//    @JsonIgnore
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//}
