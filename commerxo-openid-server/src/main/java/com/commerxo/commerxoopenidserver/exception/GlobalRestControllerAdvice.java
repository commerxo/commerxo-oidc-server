package com.commerxo.commerxoopenidserver.exception;

import com.commerxo.commerxoopenidserver.common.APIError;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalRestControllerAdvice  {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    private APIError apiError;
    private String errorMessage;

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                     HttpServletRequest request){

        errorMessage = ex.getMessage();

        apiError = new APIError(
                NOT_FOUND.value(),
                errorMessage,
                request,
                Arrays.asList(ex.getStackTrace())
        );

        logger.error("Unable to find resource with message [ " + ex.getMessage()+ " ]", ex);

        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(value = {ResourceAlreadyExistException.class})
    protected ResponseEntity<Object> handleResourceAlreadyExistException(ResourceAlreadyExistException ex,
                                                                         HttpServletRequest httpServletRequest){

        errorMessage = ex.getMessage();
        apiError = new APIError(
                CONFLICT.value(),
                errorMessage,
                httpServletRequest,
                Arrays.asList(ex.getStackTrace())
        );

        logger.error("Resource already exist with message [ " + ex.getMessage()+ " ]", ex);

        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleBadRequestException(IllegalArgumentException ex,
                                                               HttpServletRequest httpServletRequest){

        errorMessage = ex.getMessage();

        apiError = new APIError(
                BAD_REQUEST.value(),
                errorMessage,
                httpServletRequest,
                Arrays.asList(ex.getStackTrace())
        );

        logger.error("Corrupted data received with message [ " + ex.getMessage()+ " ]", ex);

        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
    }
}
