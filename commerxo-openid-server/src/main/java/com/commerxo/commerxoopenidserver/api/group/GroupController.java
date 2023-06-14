package com.commerxo.commerxoopenidserver.api.group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class GroupController {

    private static final String ENDPOINT_VERSION = "/api/v1";
    public static final String ENDPOINT = ENDPOINT_VERSION + "/group";


    @GetMapping
    @ResponseBody
    public String create(){

        return "HEllo";
    }


}
