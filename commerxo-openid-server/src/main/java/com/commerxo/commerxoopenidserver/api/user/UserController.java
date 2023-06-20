package com.commerxo.commerxoopenidserver.api.user;

import com.commerxo.commerxoopenidserver.common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.ENDPOINT)
public class UserController {

    private static final String ENDPOINT_VERSION = "/api/v1";
    public static final String ENDPOINT = ENDPOINT_VERSION + "/user";


    @RequestMapping(
            path = "/register",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> registerUser(@RequestBody UserCreationRequest request){

        return null;
    }

    @GetMapping
    public ResponseEntity<APIResponse<String>> get(){
        APIResponse<String> stringAPIResponse = new APIResponse<>(HttpStatus.BAD_REQUEST, "Data", "Success!");
        return ResponseEntity
                .status(stringAPIResponse.getStatus())
                .body(stringAPIResponse);
    }

}
