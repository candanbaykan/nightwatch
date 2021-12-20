package com.ct.nightwatch.webapi.restcontroller.api.v1.exception.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {

    UNEXPECTED_EXCEPTION(0),
    ENTITY_NOT_FOUND(1);

    private final Integer code;

    ErrorCode(Integer code) {
        this.code = code;
    }

    @JsonValue
    public Integer getCode() {
        return code;
    }

}
