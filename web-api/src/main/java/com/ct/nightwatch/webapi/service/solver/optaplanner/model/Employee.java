package com.ct.nightwatch.webapi.service.solver.optaplanner.model;

import lombok.Getter;
import lombok.Setter;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Employee {

    @PlanningId
    private Long id;

    private Integer mandatoryDay;

    private Integer level;

    private List<LocalDate> preferredDays;

    private List<LocalDate> offDays;

    public Employee() {
    }

}
