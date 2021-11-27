package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentDetails {
    private Long id;
    private String name;
    private Integer dailyWatch;
    private ManagerSummary manager;
    private List<EmployeeSummary> employees;

    public DepartmentDetails() {
    }
}
