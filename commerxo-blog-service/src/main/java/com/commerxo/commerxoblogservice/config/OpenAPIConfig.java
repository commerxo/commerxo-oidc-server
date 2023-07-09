package com.commerxo.commerxoblogservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.security.oauth2.core.oidc.OidcScopes;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Shashank Jain",
                        email = "sjaintdl@gmail.com"
                ),
                description = "OpenAPI Documents",
                title = "OpenAPI Specification",
                version = "V1.0",
                license = @License(
                        name = "Test",
                        url = "http://localhost:7895/license"
                ),
                termsOfService = "Terms of condition"
        ),
        servers = {@Server(
            url = "http://127.0.0.6:7070",
            description = "Blog API"
        )},
        security = @SecurityRequirement(
                name = "security_auth",
                scopes = {"openid", "profile"}
        )
)
@SecurityScheme(
        name = "security_auth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "${swagger.oauth2.authorization-uri}",
                        tokenUrl = "${swagger.oauth2.token-uri}",
                        scopes = {
                                @OAuthScope(
                                        name = OidcScopes.OPENID
                                ),
                                @OAuthScope(
                                        name = OidcScopes.PROFILE
                                )
                        }
                )
        ),
        in = SecuritySchemeIn.QUERY,
        description = "OAuth2.0 Authorization Server Security"
)
public class OpenAPIConfig {
}
