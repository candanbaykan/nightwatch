package com.ct.nightwatch.webapi.service.mapper;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.service.dto.EmployeeDetails;
import com.ct.nightwatch.webapi.service.dto.EmployeeRequest;
import com.ct.nightwatch.webapi.service.dto.EmployeeSummary;

public interface EmployeeMapper {

    EmployeeSummary toSummary(Employee employee);

    EmployeeDetails toDetails(Employee employee);

    Employee toEntity(Long id);

    Employee toEntity(EmployeeRequest employeeRequest);

    Employee toEntity(Long id, EmployeeRequest employeeRequest);
}
