package com.commerxo.commerxoopenidserver.api.user;

import com.commerxo.commerxoopenidserver.service.GroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.ENDPOINT)
public class UserController {

    private static final String ENDPOINT_VERSION = "/api/v1";
    public static final String ENDPOINT = ENDPOINT_VERSION + "/group";





}
