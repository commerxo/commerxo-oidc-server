package com.commerxo.commerxoopenidserver.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class UserGroup extends Auditable{

    private String id;
    private String groupName;
    private boolean enabled;
    private String description;
    private Set<GrantedAuthority> groupAuthorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GrantedAuthority> getGroupAuthorities() {
        return groupAuthorities;
    }

    public void setGroupAuthorities(Set<GrantedAuthority> groupAuthorities) {
        this.groupAuthorities = groupAuthorities;
    }
}
