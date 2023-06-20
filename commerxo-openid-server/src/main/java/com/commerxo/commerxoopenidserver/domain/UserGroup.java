package com.commerxo.commerxoopenidserver.domain;

import com.commerxo.commerxoopenidserver.domain.converter.SimpleGrantedAuthorityStringConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(
        name = "user_group",
        uniqueConstraints = @UniqueConstraint(
                name = "user_group_unique",
                columnNames = {"group_name"}
        )
)
public class UserGroup extends Auditable<String>{

    private String id;
    private String groupName;
    private String description;
    private Set<GrantedAuthority> groupAuthorities;

    @Id
    @UuidGenerator
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @CollectionTable(
            name = "group_authority",
            joinColumns = @JoinColumn(name = "id")
    )
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "authority")
    @Convert(converter = SimpleGrantedAuthorityStringConverter.class)
    public Set<GrantedAuthority> getGroupAuthorities() {
        return groupAuthorities;
    }

    public void setGroupAuthorities(Set<GrantedAuthority> groupAuthorities) {
        this.groupAuthorities = groupAuthorities;
    }

}
