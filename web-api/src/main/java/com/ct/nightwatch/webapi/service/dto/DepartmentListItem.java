package com.ct.nightwatch.webapi.service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentListItem {

    private Long id;
    private String name;
    private Integer dailyWatch;
    private ManagerSummary manager;

    public DepartmentListItem() {
    }

}
