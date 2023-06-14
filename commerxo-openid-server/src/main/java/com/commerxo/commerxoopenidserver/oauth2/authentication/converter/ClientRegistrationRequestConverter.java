package com.commerxo.commerxoopenidserver.oauth2.authentication.converter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

public final class ClientRegistrationRequestConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {

        return null;
    }


}
