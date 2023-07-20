package com.commerxo.commerxoopenidserver.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {

    @GetMapping("/register")
    public String getUserRegistrationForm(){
        return "user/register";
    }

}
