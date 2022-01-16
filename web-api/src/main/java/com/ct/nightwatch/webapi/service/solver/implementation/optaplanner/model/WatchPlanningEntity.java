package com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.time.LocalDate;
import java.util.UUID;

@PlanningEntity
@Getter
@Setter
@FieldNameConstants
public class WatchPlanningEntity {

    @PlanningId
    UUID id;

    private LocalDate date;

    @PlanningVariable(valueRangeProviderRefs = "employeeRange")
    private EmployeePlanningVariable employee;

    public WatchPlanningEntity() {
        this.id = UUID.randomUUID();
    }

}
