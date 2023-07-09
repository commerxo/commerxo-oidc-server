package com.commerxo.commerxoblogservice.http;

import java.util.Date;

public class HttpAPIResponse<T> extends HttpMessage<T>{

    private Date timestamp;

    public HttpAPIResponse(){
        this.timestamp = new Date();

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
