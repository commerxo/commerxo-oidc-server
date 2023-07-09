package com.commerxo.commerxoopenidserver.config;

import com.commerxo.commerxoopenidserver.security.provider.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
     //   String username = ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return Optional.of("anonymous");
    }
}