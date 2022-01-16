package com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.mapper.implementation.mapstruct;

import com.ct.nightwatch.webapi.repository.entity.*;
import com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.mapper.OptaModelMapper;
import com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.model.EmployeePlanningVariable;
import com.ct.nightwatch.webapi.service.solver.implementation.optaplanner.model.WatchPlanningEntity;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MsOptaModelMapper extends OptaModelMapper {

    @Override
    @Mappings({
            @Mapping(
                    source = "employee." + Employee.Fields.rank + "." + Rank.Fields.mandatoryDay,
                    target = EmployeePlanningVariable.Fields.mandatoryDay
            ),
            @Mapping(
                    source = "employee." + Employee.Fields.rank + "." + Rank.Fields.level,
                    target = EmployeePlanningVariable.Fields.level
            ),
            @Mapping(
                    source = "employee." + Employee.Fields.preferredDays,
                    target = EmployeePlanningVariable.Fields.preferredDays,
                    qualifiedByName = "List<PreferredDay> -> List<LocalDate>"
            ),
            @Mapping(
                    source = "employee." + Employee.Fields.offDays,
                    target = EmployeePlanningVariable.Fields.offDays,
                    qualifiedByName = "List<OffDay> -> List<List<LocalDate>>"
            )
    })
    EmployeePlanningVariable toOptaModel(Employee employee);

    @Override
    @BeanMapping(ignoreByDefault = true)
    @Mapping(
            source = "employeePlanningVariable." + EmployeePlanningVariable.Fields.id,
            target = Employee.Fields.id
    )
    Employee toEntity(EmployeePlanningVariable employeePlanningVariable);

    @Override
    @Mapping(
            source = "watchPlanningEntity." + WatchPlanningEntity.Fields.id,
            target = Watch.Fields.id,
            ignore = true
    )
    Watch toEntity(WatchPlanningEntity watchPlanningEntity);

    @Named("List<PreferredDay> -> List<LocalDate>")
    default List<LocalDate> preferredDayListToLocalDateList(List<PreferredDay> preferredDays) {
        return preferredDays.stream()
                .map(PreferredDay::getDate)
                .toList();
    }

    @Named("List<OffDay> -> List<List<LocalDate>>")
    default List<List<LocalDate>> offDayListToListOfLocalDateList(List<OffDay> offDays) {
        return offDays.stream()
                .map(offDay -> Arrays.asList(offDay.getBegin(), offDay.getEnd()))
                .toList();
    }

}
