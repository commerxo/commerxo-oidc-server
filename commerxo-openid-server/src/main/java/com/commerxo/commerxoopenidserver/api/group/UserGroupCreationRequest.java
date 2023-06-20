package com.commerxo.commerxoopenidserver.api.group;

import java.util.Set;

public class UserGroupCreationRequest {

    private String groupName;
    private String description;
    private Set<String> authorities;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
