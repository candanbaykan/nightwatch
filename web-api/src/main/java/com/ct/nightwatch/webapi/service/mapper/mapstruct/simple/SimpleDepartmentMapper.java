package com.ct.nightwatch.webapi.service.mapper.mapstruct.simple;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.service.dto.DepartmentSummary;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToDepartment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

// Simple mappers can be used as dependency for other mappers, but they cannot depend on other mappers
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimpleDepartmentMapper {

    DepartmentSummary toSummary(Department department);

    @IdToDepartment
    Department toEntity(Long id);

}
