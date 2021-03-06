package com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct;

import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.service.dto.EmployeeDetails;
import com.ct.nightwatch.webapi.service.dto.EmployeeListItem;
import com.ct.nightwatch.webapi.service.dto.EmployeeRequest;
import com.ct.nightwatch.webapi.service.dto.EmployeeSummary;
import com.ct.nightwatch.webapi.service.mapper.EmployeeMapper;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.complex.ComplexEmployeeMapper;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.simple.SimpleEmployeeMapper;
import org.springframework.stereotype.Component;

@Component
public class MsEmployeeMapper implements EmployeeMapper {

    private final SimpleEmployeeMapper simpleEmployeeMapper;
    private final ComplexEmployeeMapper complexEmployeeMapper;

    public MsEmployeeMapper(SimpleEmployeeMapper simpleEmployeeMapper, ComplexEmployeeMapper complexEmployeeMapper) {
        this.simpleEmployeeMapper = simpleEmployeeMapper;
        this.complexEmployeeMapper = complexEmployeeMapper;
    }

    @Override
    public EmployeeSummary toSummary(Employee employee) {
        return simpleEmployeeMapper.toSummary(employee);
    }

    @Override
    public EmployeeListItem toListItem(Employee employee) {
        return complexEmployeeMapper.toListItem(employee);
    }

    @Override
    public EmployeeDetails toDetails(Employee employee) {
        return complexEmployeeMapper.toDetails(employee);
    }

    @Override
    public Employee toEntity(Long id) {
        return simpleEmployeeMapper.toEntity(id);
    }

    @Override
    public Employee toEntity(EmployeeRequest employeeRequest) {
        return complexEmployeeMapper.toEntity(employeeRequest);
    }

    @Override
    public Employee toEntity(Long id, EmployeeRequest employeeRequest) {
        return complexEmployeeMapper.toEntity(id, employeeRequest);
    }

}
