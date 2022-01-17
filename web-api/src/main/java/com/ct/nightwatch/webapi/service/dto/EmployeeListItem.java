package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeListItem {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String mail;
    private DepartmentSummary department;
    private RankSummary rank;

    public EmployeeListItem() {
    }

}
