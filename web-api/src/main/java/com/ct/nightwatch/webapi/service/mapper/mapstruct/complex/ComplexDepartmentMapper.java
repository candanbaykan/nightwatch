package com.ct.nightwatch.webapi.service.mapper.mapstruct.complex;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.service.dto.DepartmentDetails;
import com.ct.nightwatch.webapi.service.dto.DepartmentRequest;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.qualifier.IdToManager;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleEmployeeMapper;
import com.ct.nightwatch.webapi.service.mapper.mapstruct.simple.SimpleManagerMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Complex mappers can depend on simple mappers, but they cannot depend on other complex mappers
@Mapper(
        uses = {SimpleManagerMapper.class, SimpleEmployeeMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ComplexDepartmentMapper {

    DepartmentDetails toDetails(Department department);

    @Mapping(
            source = "departmentRequest." + DepartmentRequest.Fields.managerId,
            target = Department.Fields.manager,
            qualifiedBy = IdToManager.class
    )
    Department toEntity(DepartmentRequest departmentRequest);

    @Mapping(
            source = "departmentRequest." + DepartmentRequest.Fields.managerId,
            target = Department.Fields.manager,
            qualifiedBy = IdToManager.class
    )
    Department toEntity(Long id, DepartmentRequest departmentRequest);

}
