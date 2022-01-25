package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentials {

    private String username;
    private String password;

    public UserCredentials() {
    }

}
