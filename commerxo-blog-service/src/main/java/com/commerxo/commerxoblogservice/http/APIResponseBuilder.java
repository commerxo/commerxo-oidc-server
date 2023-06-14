package com.commerxo.commerxoblogservice.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class APIResponseBuilder {

    public static ResponseEntity<Object> generateOkResponse(String message, Object payload){
        HttpStatus status = HttpStatus.OK;
        return ResponseEntity
                .status(status)
                .body(generateSuccessPayload(message, payload, status));
    }

    public static ResponseEntity<Object> generateNoContentResponse(String message, Object payload){
        HttpStatus status = HttpStatus.NO_CONTENT;
        return ResponseEntity
                .status(status)
                .body(generateSuccessPayload(message, payload, status));
    }

    private static Map<String, Object> generateSuccessPayload(String message, Object payload, HttpStatus status){
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("message", message);
        map.put("payload", payload);
        return map;
    }

}
