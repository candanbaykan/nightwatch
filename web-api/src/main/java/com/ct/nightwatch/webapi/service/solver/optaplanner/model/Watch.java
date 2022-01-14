package com.ct.nightwatch.webapi.service.solver.optaplanner.model;

import lombok.Getter;
import lombok.Setter;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.time.LocalDate;
import java.util.UUID;

@PlanningEntity
@Getter
@Setter
public class Watch {

    @PlanningId
    UUID id;

    private LocalDate date;

    @PlanningVariable(valueRangeProviderRefs = "employeeRange")
    private Employee employee;

    public Watch() {
        this.id = UUID.randomUUID();
    }

}
