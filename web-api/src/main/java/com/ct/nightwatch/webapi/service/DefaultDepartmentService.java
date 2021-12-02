package com.ct.nightwatch.webapi.service;

import com.ct.nightwatch.webapi.repository.DepartmentRepository;
import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.service.dto.DepartmentDetails;
import com.ct.nightwatch.webapi.service.dto.DepartmentRequest;
import com.ct.nightwatch.webapi.service.dto.DepartmentSummary;
import com.ct.nightwatch.webapi.service.exception.EntityNotFoundException;
import com.ct.nightwatch.webapi.service.mapper.DepartmentMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultDepartmentService implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DefaultDepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<DepartmentSummary> findAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DepartmentDetails> findById(Long id) {
        return departmentRepository.findDetailsById(id)
                .map(departmentMapper::toDetails);
    }

    @Override
    public Long save(DepartmentRequest departmentRequest) {
        return departmentRepository.save(departmentMapper.toEntity(departmentRequest)).getId();
    }

    @Override
    @Transactional
    public Optional<DepartmentDetails> updateById(Long id, DepartmentRequest departmentRequest) {
        if (!departmentRepository.existsById(id))
            throw new EntityNotFoundException(id, Department.class);

        departmentRepository.save(departmentMapper.toEntity(id, departmentRequest));
        return findById(id);
    }

    @Override
    public void deleteById(Long id) {
        try {
            departmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(id, Department.class);
        }
    }
}
