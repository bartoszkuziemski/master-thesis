package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.core.Response;

@Getter
@AllArgsConstructor
public enum Error {
    PRODUCT_NOT_FOUND("Product not found", Response.Status.NOT_FOUND),
    USER_NOT_FOUND("User not found", Response.Status.NOT_FOUND);

    private final String message;
    private final Response.StatusType httpStatus;
}
