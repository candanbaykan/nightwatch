package com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldNameConstants
public class EmployeePlanningVariable {

    @PlanningId
    private Long id;

    private Integer mandatoryDay;

    private Integer level;

    private List<LocalDate> preferredDays;

    private List<List<LocalDate>> offDays;

    public EmployeePlanningVariable() {
    }

}
