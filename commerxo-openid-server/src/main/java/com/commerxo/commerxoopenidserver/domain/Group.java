package com.commerxo.commerxoopenidserver.domain;

import com.commerxo.commerxoopenidserver.domain.converter.InstantStringConverter;
import com.commerxo.commerxoopenidserver.domain.converter.SimpleGrantedAuthorityStringConverter;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(
        name = "groups",
        uniqueConstraints = @UniqueConstraint(
                name = "groups_unique",
                columnNames = {"group_name"}
        )
)
public class Group {

    private String uuid;
    private Instant createdAt;
    private String userId;
    private String groupName;
    private Set<GrantedAuthority> groupAuthorities;
    private String description;

    @Id
    @Column(name = "group_id")
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

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "group_name", unique = true)
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
            joinColumns = @JoinColumn(
                    name = "group_id"
            )
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
