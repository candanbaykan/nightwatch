package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
public class ManagerRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String mail;
    private Long userId;

    public ManagerRequest() {
    }

}
