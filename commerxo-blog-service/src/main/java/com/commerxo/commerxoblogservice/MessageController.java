package com.commerxo.commerxoblogservice;

import com.commerxo.commerxoblogservice.http.APIResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @GetMapping("/")
    ResponseEntity<Object> message() {
        return APIResponseBuilder.generateOkResponse("Success", "Hello!");
    }
}
