package com.commerxo.commerxoblogservice.http;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class APIError {

    private Date timestamp;
    private Integer status;
    private String path;
    private String requestedMethod;
    private String message;
    private Collection<StackTraceElement> trace;

    public APIError(@Nullable HttpServletRequest request) {
        this.timestamp = new Date();
        this.path = request.getRequestURI();
        this.requestedMethod = request.getMethod();
    }

    public APIError(Integer status, String message, @Nullable HttpServletRequest request) {
        this(request);
        this.status = status;
        this.message = message;
    }

    public APIError(Integer status, String message, @Nullable HttpServletRequest request, Collection<StackTraceElement> trace) {
        this(status, message, request);
        if (!trace.isEmpty()) {
            this.trace = trace.stream().limit(10).collect(Collectors.toList());
        }
    }

    public APIError(@Nullable WebRequest request) {
        this.timestamp = new Date();
        this.path = request
                .getDescription(true)
                .substring(4).split(";")[0];
        this.requestedMethod = ((ServletWebRequest) request).getHttpMethod().toString();
    }


    public APIError(Integer status, String message, @Nullable WebRequest request) {
        this(request);
        this.status = status;
        this.message = message;
    }


    public APIError(Integer status, String message, @Nullable WebRequest request, @Nullable Collection<StackTraceElement> trace) {
        this(status, message, request);
        if (!trace.isEmpty()) {
            this.trace = trace.stream().limit(10).collect(Collectors.toList());
        }
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestedMethod() {
        return requestedMethod;
    }

    public void setRequestedMethod(String requestedMethod) {
        this.requestedMethod = requestedMethod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<StackTraceElement> getTrace() {
        return trace;
    }

    public void setTrace(Collection<StackTraceElement> trace) {
        this.trace = trace;
    }
}