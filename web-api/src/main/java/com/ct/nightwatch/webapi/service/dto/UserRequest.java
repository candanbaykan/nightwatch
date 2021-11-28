package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
public class UserRequest {

    private String username;
    private String password;
    private Long roleId;

    public UserRequest() {
    }

}
