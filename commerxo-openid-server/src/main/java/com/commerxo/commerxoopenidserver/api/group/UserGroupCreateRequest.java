package com.commerxo.commerxoopenidserver.api.group;

import com.commerxo.commerxoopenidserver.models.UserGroup;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public class UserGroupCreateRequest {

    private String groupName;
    private String description;
    private Set<String> authorities;
    private boolean enabled;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static UserGroup mapToUserGroup(UserGroupCreateRequest request){
        UserGroup group = new UserGroup();
        group.setGroupName(request.getGroupName());
        group.setDescription(request.getDescription());
        group.setEnabled(false);
        group.setGroupAuthorities(request.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
        return group;
    }

}
