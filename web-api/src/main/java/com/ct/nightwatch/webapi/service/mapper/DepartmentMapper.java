package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.service.dto.DepartmentDetails;
import com.ct.nightwatch.webapi.service.dto.DepartmentRequest;
import com.ct.nightwatch.webapi.service.dto.DepartmentSummary;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToDepartment;
import com.ct.nightwatch.webapi.service.mapper.qualifier.IdToManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ManagerMapper.class, EmployeeMapper.class})
public interface DepartmentMapper {

    DepartmentSummary toSummary(Department department);

    DepartmentDetails toDetails(Department department);

    @IdToDepartment
    Department toEntity(Long id);

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
