package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.service.dto.DepartmentDetails;
import com.ct.nightwatch.webapi.service.dto.DepartmentRequest;
import com.ct.nightwatch.webapi.service.dto.DepartmentSummary;

public interface DepartmentMapper {

    DepartmentSummary toSummary(Department department);

    DepartmentDetails toDetails(Department department);

    Department toEntity(Long id);

    Department toEntity(DepartmentRequest departmentRequest);

    Department toEntity(Long id, DepartmentRequest departmentRequest);

}
