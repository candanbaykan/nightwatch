package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationToken {

    private String token;

    public AuthenticationToken() {
    }

    public AuthenticationToken(String token) {
        this.token = token;
    }

}
