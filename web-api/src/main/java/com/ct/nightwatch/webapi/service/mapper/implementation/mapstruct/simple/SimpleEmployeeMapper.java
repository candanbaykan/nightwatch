package com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.simple;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.service.dto.EmployeeSummary;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.qualifier.IdToEmployee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

// Simple mappers can be used as dependency for other mappers, but they cannot depend on other mappers
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimpleEmployeeMapper {

    EmployeeSummary toSummary(Employee employee);

    @IdToEmployee
    Employee toEntity(Long id);

}
