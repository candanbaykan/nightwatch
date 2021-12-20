package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.EmployeeDetails;
import com.ct.nightwatch.webapi.service.dto.EmployeeRequest;
import com.ct.nightwatch.webapi.service.dto.EmployeeSummary;

import java.util.List;

public interface EmployeeService {

    List<EmployeeSummary> findAll();

    EmployeeDetails findById(Long id);

    Long save(EmployeeRequest employeeRequest);

    void updateById(Long id, EmployeeRequest employeeRequest);

    void deleteById(Long id);

}
