package com.commerxo.commerxoblogservice.security.jwt.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public final class RolesJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String DEFAULT_ROLE_PREFIX = "ROLE_";
    private static final String DEFAULT_CLAIM_NAME = "roles";
    private static final String DEFAULT_AUTHORITIES_CLAIM_DELIMITER = " ";

    @Override
    public Collection<GrantedAuthority> convert(@Nullable Jwt jwt) {
        if(jwt == null)
            return Collections.emptyList();
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String authority : getAuthorities(jwt)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(DEFAULT_ROLE_PREFIX + authority));
        }
        return grantedAuthorities;
    }

    private Collection<String> getAuthorities(Jwt jwt){
        Object authorities = jwt.getClaim(DEFAULT_CLAIM_NAME);
        if(authorities instanceof String){
            if (StringUtils.hasText((String) authorities)) {
                return Arrays.asList(((String) authorities).split(DEFAULT_AUTHORITIES_CLAIM_DELIMITER));
            }
            return Collections.emptyList();
        }
        if (authorities instanceof Collection) {
            return castAuthoritiesToCollection(authorities);
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    private Collection<String> castAuthoritiesToCollection(Object authorities) {
        return (Collection<String>) authorities;
    }
}
