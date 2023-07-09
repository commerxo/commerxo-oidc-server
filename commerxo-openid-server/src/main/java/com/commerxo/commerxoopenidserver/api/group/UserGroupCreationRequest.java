package com.commerxo.commerxoopenidserver.api.group;

import com.commerxo.commerxoopenidserver.domain.UserGroup;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

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

    public static UserGroup mapToEntity(UserGroupCreationRequest request){
        UserGroup group = new UserGroup();
        group.setGroupName(request.getGroupName());
        group.setDescription(request.getDescription());
        group.setGroupAuthorities(grantedAuthorities(request.getAuthorities()));
        return group;
    }

    private static Set<GrantedAuthority> grantedAuthorities(Set<String> authorities){
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(authorities.size());
        for(String authority: authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        return grantedAuthorities;
    }
}
