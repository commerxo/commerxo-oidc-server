package com.commerxo.commerxoblogservice.http;

import org.springframework.http.HttpStatus;

public interface Response<T>{

    HttpStatus getStatus();

    String error();

    T getPayload();

    Class<?> getResponseClassName();

}
