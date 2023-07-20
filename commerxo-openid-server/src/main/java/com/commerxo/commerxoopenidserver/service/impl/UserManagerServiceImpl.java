package com.commerxo.commerxoopenidserver.service.impl;

import com.commerxo.commerxoopenidserver.api.user.UserRegisterRequest;
import com.commerxo.commerxoopenidserver.models.User;
import com.commerxo.commerxoopenidserver.repository.UserRepository;
import com.commerxo.commerxoopenidserver.service.UserManagerService;
import com.commerxo.commerxoopenidserver.service.validator.UserRegistrationRequestValidator;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class UserManagerServiceImpl implements UserManagerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Consumer<UserRegisterRequest> userRegistrationRequestValidator =  new UserRegistrationRequestValidator();

    public UserManagerServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
//    public void Post(){
//        UserRegisterRequest request = new UserRegisterRequest();
////        request.setUsername("user1"+ new Random(6).nextInt(4));
//        request.setPassword(this.passwordEncoder.encode("pass"));
//        request.setEmailId("1234@g.com");
//        create(request);
//    }

    @Override
    public User create(UserRegisterRequest request) {
        this.userRegistrationRequestValidator.accept(request);
        User newUser = User.withId(UUID.randomUUID().toString())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .username(request.getUsername())
                .emailId(request.getEmailId())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .build();
        this.userRepository.save(newUser);
        // Once saved assign UserGroup for authority
        return newUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

}
