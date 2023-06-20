package com.commerxo.commerxoblogservice.api;

import com.commerxo.commerxoblogservice.http.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @GetMapping("/")
    ResponseEntity<APIResponse<String>> message() {
        APIResponse<String> apiResponse = new APIResponse<>(HttpStatus.OK, "Hello!", "Success");
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
}
