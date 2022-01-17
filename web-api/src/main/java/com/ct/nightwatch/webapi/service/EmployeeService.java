package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.service.dto.EmployeeDetails;
import com.ct.nightwatch.webapi.service.dto.EmployeeListItem;
import com.ct.nightwatch.webapi.service.dto.EmployeeRequest;
import com.ct.nightwatch.webapi.service.dto.EmployeeSummary;

import java.util.List;

public interface EmployeeService {

    List<EmployeeListItem> findAll();

    EmployeeDetails findById(Long id);

    List<EmployeeListItem> findByManagerId(Long managerId);

    Long save(EmployeeRequest employeeRequest);

    void updateById(Long id, EmployeeRequest employeeRequest);

    void deleteById(Long id);

}
