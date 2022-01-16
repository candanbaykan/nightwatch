package com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.mapper;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.repository.entity.Watch;
import com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.model.EmployeePlanningVariable;
import com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.model.WatchPlanningEntity;

public interface OptaModelMapper {

    EmployeePlanningVariable toOptaModel(Employee employee);

    Employee toEntity(EmployeePlanningVariable employeePlanningVariable);

    Watch toEntity(WatchPlanningEntity watchPlanningEntity);

}
