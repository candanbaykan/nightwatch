package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.repository.PreferredDayRepository;
import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.repository.entity.PreferredDay;
import com.ct.nightwatch.webapi.service.PreferredDayService;
import com.ct.nightwatch.webapi.service.dto.PreferredDayDetails;
import com.ct.nightwatch.webapi.service.dto.PreferredDayRequest;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.DepartmentMapper;
import com.ct.nightwatch.webapi.service.mapper.PreferredDayMapper;
import com.ct.nightwatch.webapi.service.specification.PreferredDaySpecification;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultPreferredDayService implements PreferredDayService {

    private final PreferredDayRepository preferredDayRepository;
    private final PreferredDayMapper preferredDayMapper;
    private final DepartmentMapper departmentMapper;

    public DefaultPreferredDayService(
            PreferredDayRepository preferredDayRepository,
            PreferredDayMapper preferredDayMapper, DepartmentMapper departmentMapper) {

        this.preferredDayRepository = preferredDayRepository;
        this.preferredDayMapper = preferredDayMapper;
        this.departmentMapper = departmentMapper;
    }

    @Override
    @PreAuthorize("@authService.isAdmin() || " +
            "(#parameters['employeeId'] != null && " +
            "(@authService.isEmployeeManager(#parameters['employeeId']) || " +
            "@authService.isEqualEmployee(#parameters['employeeId'])))")
    public List<PreferredDayDetails> findAll(Map<String, String> parameters) {
        PreferredDaySpecification specification = new PreferredDaySpecification(parameters);
        return preferredDayRepository.findAll(specification).stream()
                .map(preferredDayMapper::toDetails)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("@authService.canViewPreferredDay(#id)")
    public PreferredDayDetails findById(Long id) {
        return preferredDayRepository.findDetailsById(id)
                .map(preferredDayMapper::toDetails)
                .orElseThrow(() -> new EntityNotFoundException(id, PreferredDay.class));
    }

    @Override
    @PreAuthorize("@authService.isAdmin() || " +
            "@authService.isDepartmentManager(#departmentId)")
    public List<PreferredDayDetails> findByDepartmentId(Long departmentId) {
        Department department = departmentMapper.toEntity(departmentId);
        return preferredDayRepository.findByEmployeeDepartment(department).stream()
                .map(preferredDayMapper::toDetails)
                .toList();
    }

    @Override
    @PreAuthorize("#preferredDayRequest != null &&" +
            "#preferredDayRequest.employeeId != null && " +
            "@authService.isEqualEmployee(#preferredDayRequest.employeeId)")
    public Long save(PreferredDayRequest preferredDayRequest) {
        PreferredDay preferredDay = preferredDayMapper.toEntity(preferredDayRequest);
        return preferredDayRepository.save(preferredDay).getId();
    }

    @Override
    @PreAuthorize("@authService.canModifyPreferredDay(#id)")
    public void updateById(Long id, PreferredDayRequest preferredDayRequest) {
        if (!preferredDayRepository.existsById(id))
            throw new EntityNotFoundException(id, PreferredDay.class);

        PreferredDay preferredDay = preferredDayMapper.toEntity(id, preferredDayRequest);
        preferredDayRepository.save(preferredDay);
    }

    @Override
    @PreAuthorize("@authService.canModifyPreferredDay(#id)")
    public void deleteById(Long id) {
        try {
            preferredDayRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(id, PreferredDay.class);
        }
    }
}
