package com.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @Override
    public Response toResponse(ApplicationException exception) {
        String message = exception.getError().getMessage();
        LOGGER.warn(message);
        return Response.status(exception.getError().getHttpStatus()).entity(new ErrorInfo(message)).build();
    }
}
