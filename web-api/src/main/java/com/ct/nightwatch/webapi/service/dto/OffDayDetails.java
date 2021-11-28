package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OffDayDetails {

    private Long id;
    private Date begin;
    private Date end;
    private EmployeeSummary employee;

    public OffDayDetails() {
    }

}
