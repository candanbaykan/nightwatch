package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.common.utility.trimmer.annotation.Trim;
import com.ct.nightwatch.webapi.repository.EmployeeRepository;
import com.ct.nightwatch.webapi.repository.entity.Employee;
import com.ct.nightwatch.webapi.repository.entity.Manager;
import com.ct.nightwatch.webapi.service.EmployeeService;
import com.ct.nightwatch.webapi.service.dto.EmployeeDetails;
import com.ct.nightwatch.webapi.service.dto.EmployeeListItem;
import com.ct.nightwatch.webapi.service.dto.EmployeeRequest;
import com.ct.nightwatch.webapi.service.dto.EmployeeSummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.EmployeeMapper;
import com.ct.nightwatch.webapi.service.mapper.ManagerMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final ManagerMapper managerMapper;

    public DefaultEmployeeService(EmployeeRepository employeeRepository,
                                  EmployeeMapper employeeMapper,
                                  ManagerMapper managerMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.managerMapper = managerMapper;
    }

    @Override
    public List<EmployeeListItem> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toListItem)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDetails findById(Long id) {
        return employeeRepository.findDetailsById(id)
                .map(employeeMapper::toDetails)
                .orElseThrow(() -> new EntityNotFoundException(id, Employee.class));
    }

    @Override
    public List<EmployeeListItem> findByManagerId(Long managerId) {
        Manager manager = managerMapper.toEntity(managerId);
        return employeeRepository.findByDepartmentManager(manager).stream()
                .map(employeeMapper::toListItem)
                .toList();
    }

    @Override
    public Long save(@Trim EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toEntity(employeeRequest);
        return employeeRepository.save(employee).getId();
    }

    @Override
    public void updateById(Long id, @Trim EmployeeRequest employeeRequest) {
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
