package com.ct.nightwatch.webapi.service.implementation;

import com.ct.nightwatch.webapi.repository.OffDayRepository;
import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.repository.entity.OffDay;
import com.ct.nightwatch.webapi.service.OffDayService;
import com.ct.nightwatch.webapi.service.dto.OffDayDetails;
import com.ct.nightwatch.webapi.service.dto.OffDayRequest;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.DepartmentMapper;
import com.ct.nightwatch.webapi.service.mapper.OffDayMapper;
import com.ct.nightwatch.webapi.service.specification.OffDaySpecification;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultOffDayService implements OffDayService {

    private final OffDayRepository offDayRepository;
    private final OffDayMapper offDayMapper;
    private final DepartmentMapper departmentMapper;

    public DefaultOffDayService(OffDayRepository offDayRepository, OffDayMapper offDayMapper, DepartmentMapper departmentMapper) {
        this.offDayRepository = offDayRepository;
        this.offDayMapper = offDayMapper;
        this.departmentMapper = departmentMapper;
    }

    @Override
    @PreAuthorize("@authService.isAdmin() || " +
            "(#parameters['employeeId'] != null && " +
            "@authService.isEmployeeManager(#parameters['employeeId']) || " +
            "@authService.isEqualEmployee(#parameters['employeeId']))")
    public List<OffDayDetails> findAll(Map<String, String> parameters) {
        OffDaySpecification specification = new OffDaySpecification(parameters);
        return offDayRepository.findAll(specification).stream()
                .map(offDayMapper::toDetails)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("@authService.canViewOffDay(#id)")
    public OffDayDetails findById(Long id) {
        return offDayRepository.findDetailsById(id)
                .map(offDayMapper::toDetails)
                .orElseThrow(() -> new EntityNotFoundException(id, OffDay.class));
    }

    @Override
    @PreAuthorize("@authService.isAdmin() || " +
            "@authService.isDepartmentManager(#departmentId)")
    public List<OffDayDetails> findByDepartmentId(Long departmentId) {
        Department department = departmentMapper.toEntity(departmentId);
        return offDayRepository.findByEmployeeDepartment(department).stream()
                .map(offDayMapper::toDetails)
                .toList();
    }

    @Override
    @PreAuthorize("@authService.isAdmin() || " +
            "(#offDayRequest != null && " +
            "#offDayRequest.employeeId != null && " +
            "@authService.isEmployeeManager(#offDayRequest.employeeId))")
    public Long save(OffDayRequest offDayRequest) {
        OffDay offDay = offDayMapper.toEntity(offDayRequest);
        return offDayRepository.save(offDay).getId();
    }

    @Override
    @PreAuthorize("@authService.canModifyOffDay(#id, #offDayRequest)")
    public void updateById(Long id, OffDayRequest offDayRequest) {
        if (!offDayRepository.existsById(id))
            throw new EntityNotFoundException(id, OffDay.class);

        OffDay offDay = offDayMapper.toEntity(id, offDayRequest);
        offDayRepository.save(offDay);
    }

    @Override
    @PreAuthorize("@authService.canModifyOffDay(#id)")
    public void deleteById(Long id) {
        try {
            offDayRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(id, OffDay.class);
        }
    }

}
