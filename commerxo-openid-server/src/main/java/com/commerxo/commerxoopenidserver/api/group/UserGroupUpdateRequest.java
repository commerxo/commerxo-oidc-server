package com.commerxo.commerxoopenidserver.api.group;

import java.util.Set;

public class UserGroupUpdateRequest {

    private String description;
    private boolean enabled =  false;
    private Set<String> authorities;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

}
