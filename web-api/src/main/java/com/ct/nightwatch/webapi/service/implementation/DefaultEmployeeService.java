package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.repository.EmployeeRepository;
import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.service.EmployeeService;
import com.ct.nightwatch.webapi.service.dto.EmployeeDetails;
import com.ct.nightwatch.webapi.service.dto.EmployeeRequest;
import com.ct.nightwatch.webapi.service.dto.EmployeeSummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.EmployeeMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public DefaultEmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeSummary> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDetails> findById(Long id) {
        return employeeRepository.findDetailsById(id)
                .map(employeeMapper::toDetails);
    }

    @Override
    public Long save(EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toEntity(employeeRequest);
        return employeeRepository.save(employee).getId();
    }

    @Override
    public void updateById(Long id, EmployeeRequest employeeRequest) {
        if (!employeeRepository.existsById(id))
            throw new EntityNotFoundException(id, Employee.class);

        Employee employee = employeeMapper.toEntity(id, employeeRequest);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(id, Employee.class);
        }
    }
}
