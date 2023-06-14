package com.commerxo.commerxoblogservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class JWTSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz.requestMatchers(HttpMethod.GET, "/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/**")
                        .hasAuthority("SCOPE_write")
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2->
                        oauth2.jwt(jwt->
                                jwt.jwkSetUri(" http://localhost:9090/oauth2/jwks")
                        )
                );
        return http.build();
    }
}
