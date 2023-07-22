package com.commerxo.commerxoopenidserver.service.validator;

import com.commerxo.commerxoopenidserver.api.user.UserRegisterRequest;
import org.springframework.util.StringUtils;

import java.util.function.Consumer;


public class UserRegistrationRequestValidator implements Consumer<UserRegisterRequest> {

    @Override
    public void accept(UserRegisterRequest request) {

        if(!StringUtils.hasText(request.getUsername()))
            throw new IllegalArgumentException("Username can't be empty!");

        if(!StringUtils.hasText(request.getPassword()))
            throw new IllegalArgumentException("Password can't be empty!");

        if(!StringUtils.hasText(request.getEmailId()))
            throw new IllegalArgumentException("EmailId can't be empty!");

    }

}
