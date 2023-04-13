package com.example.masterthesisspring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorInfo> handleApplicationException(ApplicationException exception) {
        String message = exception.getError().getMessage();
        LOGGER.warn(message);
        return new ResponseEntity<>(new ErrorInfo(message), exception.getError().getHttpStatus());
    }

}
