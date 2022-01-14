package com.ct.nightwatch.webapi.service.solver.optaplanner;

import com.ct.nightwatch.webapi.service.solver.optaplanner.model.Employee;
import com.ct.nightwatch.webapi.service.solver.optaplanner.model.Watch;
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
public class WatchSolution {

    @PlanningEntityCollectionProperty
    private List<Watch> watches;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "employeeRange")
    private List<Employee> employees;

    @PlanningScore
    private HardSoftScore score;

    public WatchSolution() {
    }

    public WatchSolution(List<Watch> watches, List<Employee> employees) {
        this.watches = watches;
        this.employees = employees;
    }

}
