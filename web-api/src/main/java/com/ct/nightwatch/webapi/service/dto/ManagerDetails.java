package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ManagerDetails {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String mail;
    private List<DepartmentSummary> departments;
    private UserSummary user;

    public ManagerDetails() {
    }
}
