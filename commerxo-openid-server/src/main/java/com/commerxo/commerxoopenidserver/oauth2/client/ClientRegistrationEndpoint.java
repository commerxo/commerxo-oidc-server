//package com.commerxo.commerxoopenidserver.oauth2.client;
//
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/client")
//public class ClientRegistrationEndpoint {
//
//    private final RegisteredClientRepository repository;
//
//    public ClientRegistrationEndpoint(RegisteredClientRepository repository) {
//        this.repository = repository;
//    }
//
//    @RequestMapping(
//            method = RequestMethod.GET,
//            path = "/register"
//    )
//    @ResponseBody
//    public String register(){
//        return "Hello";
//    }
//
//
//}
