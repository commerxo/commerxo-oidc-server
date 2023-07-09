package com.commerxo.commerxoopenidserver.service;

import com.commerxo.commerxoopenidserver.domain.User;
import com.commerxo.commerxoopenidserver.repository.UserRepository;
import com.commerxo.commerxoopenidserver.security.provider.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Service
public class DefaultUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public DefaultUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("User does not exist with Username ==> " + username);

        return UserPrincipal.buildWithId(user.getId())
                .username(user.getUsername())
                .emailId(user.getEmailId())
                .enabled(user.isEnabled())
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .credentialsExpired(user.isCredentialsNonExpired())
                .build();
    }

}
//Need to assign Authentication provider to authentication manager
