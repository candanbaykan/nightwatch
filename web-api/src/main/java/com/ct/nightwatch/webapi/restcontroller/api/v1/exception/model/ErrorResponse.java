package com.ct.nightwatch.webapi.restcontroller.api.v1.exception.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ErrorResponse<T> {

    private Instant timestamp;
    private ErrorCode code;
    private T message;
    private String path;

    public ErrorResponse() {
        this.timestamp = Instant.now();
    }

    public ErrorResponse(ErrorCode code, T message, String path) {
        this();
        this.code = code;
        this.message = message;
        this.path = path;
    }
}
