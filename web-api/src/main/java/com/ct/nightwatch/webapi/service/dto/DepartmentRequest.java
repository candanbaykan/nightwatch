package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequest {
    private String name;
    private Integer dailyWatch;
    private Long managerId;

    public DepartmentRequest() {
    }
}
