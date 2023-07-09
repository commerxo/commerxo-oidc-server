package com.commerxo.commerxoclientserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;

import java.util.Set;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
        http.oauth2Login((oauth2) ->
                oauth2.clientRegistrationRepository(clientRegistrationRepository())
                .authorizedClientService(authorizedClientService())
        );
        return http.build();
    }


    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(
                clientRegistrationRepository());
    }



    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        ClientRegistration springClient = ClientRegistration.withRegistrationId("client")
                .clientId("client")
                .clientName("Spring Client")
                .clientSecret("secret")
                .redirectUri("http://127.0.0.5:8080/login/oauth2/code/client")
                .authorizationUri("http://localhost:9090/oauth2/authorize")
                .tokenUri("http://localhost:9090/oauth2/token")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .scope(Set.of("openid", "profile","blog:read", "blog:write", "blog:delete"))
                .issuerUri("http://localhost:9090")
                .jwkSetUri("http://localhost:9090/oauth2/jwks")
                .build();

        return new InMemoryClientRegistrationRepository(springClient);
    }
}
