package com.commerxo.commerxoblogservice.security;

import org.springframework.security.oauth2.jwt.Jwt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class JwtPrincipal {

    private static final String DEFAULT_ROLE_PREFIX = "ROLE_";
    private static final String DEFAULT_SCOPE_PREFIX = "SCOPE_";

    private String jti;
    private Jwt jwt;
    private String issuer;
    private String subject;
    private String username;
    private String tokenValue;
    private List<String> roles;
    private List<String> scopes;
    private List<String> audience;

    public String getJti() {
        return jti;
    }

    public Jwt getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public String getSubject() {
        return subject;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public List<String> getAudience() {
        return audience;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static Builder withJwt(Jwt jwt){
        if (jwt == null)
            throw new IllegalArgumentException("Jwt can't be null!");
        return new Builder(jwt);
    }

    public static class Builder implements Serializable{

        private String jti;
        private final Jwt jwt;
        private String username;
        private String issuer;
        private String tokenValue;
        private String subject;
        private final List<String> roles;
        private final List<String> scopes;
        private final List<String> audience;

        public Builder(Jwt jwt){
            this.jti = jwt.getId();
            this.jwt = jwt;
            this.username = jwt.getSubject();
            this.subject = jwt.getSubject();
            this.audience = jwt.getAudience();
            this.issuer = jwt.getIssuer().toString();
            this.scopes = getClaimAsList(jwt, DEFAULT_SCOPE_PREFIX, ClaimNames.SCOPES);
            this.roles = getClaimAsList(jwt, DEFAULT_ROLE_PREFIX, ClaimNames.ROLES);
            this.tokenValue = jwt.getTokenValue();
        }

        public Builder jti(String jti){
            this.jti = jti;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder subject(String subject){
            this.subject = subject;
            return this;
        }

        public Builder audience(Consumer<List<String>> audienceConsumer){
            audienceConsumer.accept(this.audience);
            return this;
        }

        public Builder token(String tokenValue){
            this.tokenValue = tokenValue;
            return this;
        }

        public Builder issuer(String issuer){
            this.issuer = issuer;
            return this;
        }

        public Builder roles(Consumer<List<String>> roles){
            roles.accept(this.roles);
            return this;
        }

        public Builder scopes(Consumer<List<String>> scopeConsumer){
            scopeConsumer.accept(this.scopes);
            return this;
        }

        @SuppressWarnings("unchecked")
        private List<String> getClaimAsList(Jwt jwt, String prefix, String claimName){
            if(jwt.getClaim(claimName) != null){
                List<String> claims = new ArrayList<>();
                for (String claim: (List<String>)jwt.getClaim(claimName)){
                    claims.add(prefix + claim);
                }
                return claims;
            }
            return Collections.emptyList();
        }

        public JwtPrincipal build(){
            JwtPrincipal principal = new JwtPrincipal();
            principal.jti = this.jti;
            principal.jwt = this.jwt;
            principal.username = this.username;
            principal.subject = this.subject;
            principal.issuer = this.issuer;
            principal.tokenValue = this.tokenValue;
            principal.audience = this.audience.isEmpty() ? Collections.emptyList() : this.audience;
            principal.scopes = this.scopes.isEmpty() ? Collections.emptyList() : this.scopes;
            principal.roles = this.roles.isEmpty() ? Collections.emptyList() : this.roles;
            return principal;
        }
    }

    private static class ClaimNames {

        public static final String ROLES = "roles";

        public static final String SCOPES = "scope";


    }
}
