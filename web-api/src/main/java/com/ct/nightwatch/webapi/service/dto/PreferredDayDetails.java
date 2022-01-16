package com.ct.nightwatch.webapi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PreferredDayDetails {

    private Long id;
    private LocalDate date;
    private EmployeeSummary employee;

    public PreferredDayDetails() {
    }

}
