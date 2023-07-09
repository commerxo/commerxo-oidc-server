package com.commerxo.commerxoblogservice.api;

import com.commerxo.commerxoblogservice.http.HttpAPIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MessageController {
    @GetMapping("/")
    HttpAPIResponse<String> message() {
        return null;
    }
}
