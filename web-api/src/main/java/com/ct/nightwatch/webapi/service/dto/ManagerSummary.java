package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerSummary {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String mail;

    public ManagerSummary() {
    }

}
