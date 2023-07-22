package com.commerxo.commerxoopenidserver.service.impl;

import com.commerxo.commerxoopenidserver.api.user.UserRegisterRequest;
import com.commerxo.commerxoopenidserver.models.User;
import com.commerxo.commerxoopenidserver.repository.UserRepository;
import com.commerxo.commerxoopenidserver.service.UserManagerService;
import com.commerxo.commerxoopenidserver.service.validator.UserRegistrationRequestValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;

@Service
public class UserManagerServiceImpl implements UserManagerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Consumer<UserRegisterRequest> userRegistrationRequestValidator =  new UserRegistrationRequestValidator();

    public UserManagerServiceImpl(PasswordEncoder passwordEncoder,
                                  UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(UserRegisterRequest request) {
        if(request == null)
            throw new IllegalArgumentException("UserRegisterRequest, can't be null!");

        this.userRegistrationRequestValidator.accept(request);

        User newUser = User.withId(UUID.randomUUID().toString())
                .username(request.getUsername())
                .emailId(request.getEmailId())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        this.userRepository.insert(newUser);
        // Once saved assign UserGroup for authority
        return newUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

}
