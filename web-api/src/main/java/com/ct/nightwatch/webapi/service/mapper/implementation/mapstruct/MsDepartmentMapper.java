package com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct;

import com.ct.nightwatch.webapi.repository.entity.Department;
import com.ct.nightwatch.webapi.service.dto.DepartmentDetails;
import com.ct.nightwatch.webapi.service.dto.DepartmentListItem;
import com.ct.nightwatch.webapi.service.dto.DepartmentRequest;
import com.ct.nightwatch.webapi.service.dto.DepartmentSummary;
import com.ct.nightwatch.webapi.service.mapper.DepartmentMapper;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.complex.ComplexDepartmentMapper;
import com.ct.nightwatch.webapi.service.mapper.implementation.mapstruct.simple.SimpleDepartmentMapper;
import org.springframework.stereotype.Component;

@Component
public class MsDepartmentMapper implements DepartmentMapper {

    private final SimpleDepartmentMapper simpleDepartmentMapper;
    private final ComplexDepartmentMapper complexDepartmentMapper;

    public MsDepartmentMapper(
            SimpleDepartmentMapper simpleDepartmentMapper,
            ComplexDepartmentMapper complexDepartmentMapper
    ) {
        this.simpleDepartmentMapper = simpleDepartmentMapper;
        this.complexDepartmentMapper = complexDepartmentMapper;
    }

    @Override
    public DepartmentSummary toSummary(Department department) {
        return simpleDepartmentMapper.toSummary(department);
    }

    @Override
    public DepartmentListItem toListItem(Department department) {
        return complexDepartmentMapper.toListItem(department);
    }

    @Override
    public DepartmentDetails toDetails(Department department) {
        return complexDepartmentMapper.toDetails(department);
    }

    @Override
    public Department toEntity(Long id) {
        return simpleDepartmentMapper.toEntity(id);
    }

    @Override
    public Department toEntity(DepartmentRequest departmentRequest) {
        return complexDepartmentMapper.toEntity(departmentRequest);
    }

    @Override
    public Department toEntity(Long id, DepartmentRequest departmentRequest) {
        return complexDepartmentMapper.toEntity(id, departmentRequest);
    }

}
