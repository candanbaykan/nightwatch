package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeDetails {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String mail;
    private DepartmentSummary department;
    private RankSummary rank;
    private UserSummary user;
    private List<OffDaySummary> offDays;
    private List<PreferredDaySummary> preferredDays;
    private List<WatchSummary> watches;

    public EmployeeDetails() {
    }
}
