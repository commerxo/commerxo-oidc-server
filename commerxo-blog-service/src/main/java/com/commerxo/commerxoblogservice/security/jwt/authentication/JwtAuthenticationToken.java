package com.commerxo.commerxoblogservice.security.jwt.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;

import java.util.Collection;
import java.util.Map;

public class JwtAuthenticationToken extends AbstractOAuth2TokenAuthenticationToken<Jwt> {

    private final String name;

    public JwtAuthenticationToken(Jwt token, Object principal, Object credentials,  Collection<? extends GrantedAuthority> authorities, String name) {
        super(token, principal, credentials, authorities);
        setAuthenticated(true);
        this.name = name;
    }

    @Override
    public Map<String, Object> getTokenAttributes() {
        return this.getToken().getClaims();
    }

    @Override
    public String getName() {
        return this.name;
    }
}
