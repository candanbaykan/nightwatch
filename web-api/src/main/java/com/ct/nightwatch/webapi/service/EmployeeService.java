package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.EmployeeDetails;
import com.ct.nightwatch.webapi.service.dto.EmployeeRequest;
import com.ct.nightwatch.webapi.service.dto.EmployeeSummary;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeSummary> findAll();

    Optional<EmployeeDetails> findById(Long id);

    Long save(EmployeeRequest employeeRequest);

    Optional<EmployeeDetails> updateById(Long id, EmployeeRequest employeeRequest);

    void deleteById(Long id);

}
