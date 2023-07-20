package com.commerxo.commerxoopenidserver.service;

import com.commerxo.commerxoopenidserver.api.user.UserRegisterRequest;
import com.commerxo.commerxoopenidserver.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserManagerService extends UserDetailsService {

    User create(UserRegisterRequest request);

}
