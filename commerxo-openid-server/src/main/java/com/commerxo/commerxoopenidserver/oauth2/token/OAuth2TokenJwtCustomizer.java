package com.commerxo.commerxoopenidserver.oauth2.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.List;

@Configuration
public class OAuth2TokenJwtCustomizer {

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(){
        return context -> {
            if(context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)){
                context.getClaims()
                        .claim("roles", List.of("FULL","ADMIN"));
                // Return token based on client and assign roles and scopes.
            }
            if(context.getTokenType().getValue().equals(OidcParameterNames.ID_TOKEN)){
                context.getClaims()
                        .claim("Granted Authorities","FULL");
            }
        };
    }

}