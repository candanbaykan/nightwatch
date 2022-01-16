package com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.model;

import lombok.Getter;
import lombok.Setter;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
@Getter
@Setter
public class WatchPlanningSolution {

    @PlanningEntityCollectionProperty
    private List<WatchPlanningEntity> watches;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "employeeRange")
    private List<EmployeePlanningVariable> employees;

    @PlanningScore
    private HardSoftScore score;

    public WatchPlanningSolution() {
    }

    public WatchPlanningSolution(
            List<WatchPlanningEntity> watches,
            List<EmployeePlanningVariable> employees
    ) {
        this.watches = watches;
        this.employees = employees;
    }

}
