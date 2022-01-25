package com.ct.nightwatch.webapi.restcontroller.api.v1.exception.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {

    UNEXPECTED_EXCEPTION(0),
    UNAUTHORIZED(1),
    ACCESS_DENIED(2),
    BAD_CREDENTIALS(3),
    ENTITY_NOT_FOUND(4);

    private final Integer code;

    ErrorCode(Integer code) {
        this.code = code;
    }

    @JsonValue
    public Integer getCode() {
        return code;
    }

}
