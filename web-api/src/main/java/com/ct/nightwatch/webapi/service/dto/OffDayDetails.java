package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OffDayDetails {

    private Long id;
    private LocalDate begin;
    private LocalDate end;
    private EmployeeSummary employee;

    public OffDayDetails() {
    }

}
