package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentSummary {

    private Long id;
    private String name;
    private Integer dailyWatch;

    public DepartmentSummary() {
    }

}
