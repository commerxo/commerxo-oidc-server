package com.commerxo.commerxoopenidserver.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/register")
    public String getUserRegistrationForm(){
        return "user/register";
    }


}